package com.augmentum.oes.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.jdbc.core.JdbcTemplate;

import com.augmentum.oes.dao.QuestionDao;
import com.augmentum.oes.exception.DBException;
import com.augmentum.oes.model.Question;
import com.augmentum.oes.util.TransactionUtil;

public class QuestionDaoImpl implements QuestionDao {

    private QueryRunner qr = new QueryRunner(TransactionUtil.getDataSource());

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    }

    @Override
    public Integer createQuestion(Question question) {
        try {
            qr.update("INSERT INTO question(display_id, question_content, option_one, option_two, option_three, option_four, correct_option) VALUES(?, ?, ?, ?, ?, ?, ?)",question.getDisplayId(), question.getQuestionContent(), question.getOptionOne(), question.getOptionTwo(), question.getOptionThree(), question.getOptionFour(), question.getCorrectOption());
        } catch (SQLException e) {
            throw new DBException();
        }
        return question.getId();
    }

    @Override
    public void deleteQuestionById(String display_id) {
        try {
            qr.update("UPDATE question SET isdelete = true  WHERE display_id=?", display_id);
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public void updateQuestion(Question question) {
        try {
            Question previous_question = qr.query("SELECT * FROM question WHERE id = ?", new BeanHandler<Question>(Question.class), question.getPreviousId());
            int version = Integer.parseInt(previous_question.getPreviousId());
            qr.update("INSERT INTO question(display_id, question_content,option_one, option_two,option_three, option_four, correct_option, previous_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)", question.getDisplayId(), question.getQuestionContent(), question.getOptionOne(), question.getOptionTwo(), question.getOptionThree(), question.getOptionFour(), question.getCorrectOption(), version+1);
            qr.update("UPDATE question set previous_id =? WHERE id=?", 1, question.getPreviousId());
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public Question getQuestionById(String id) {
        try {
            return qr.query("SELECT * FROM question WHERE id =? AND isdelete != true AND previous_id =0", new BeanHandler<Question>(Question.class),id);
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public List<Question> findQuestions() {
        try {
            return qr.query("SELECT * FROM question WHERE isdelete != true AND previous_id =0 ORDER BY id DESC LIMIT 0,10 ", new BeanListHandler<Question>(Question.class));
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public List<Question> findQuestionsByKeyWord(String keywords) {
        try {
            if (keywords.contains("%")) {
                keywords = keywords.replace("%", "\\%");
            }
            String sql = "SELECT * FROM question WHERE question_content LIKE ? ? ? AND isdelete != true AND previous_id =0";
            return qr.query(sql, new BeanListHandler<Question>(Question.class), "%", keywords,"%");
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public int getTotalRecords(){
        try {
            return  (int) qr.query("SELECT count(*) FROM question WHERE isdelete != true AND previous_id =0",new ScalarHandler(1));
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public List<Question> findPageRecords(int startIndex, int pagesize) {
        try {
            return qr.query("SELECT * FROM question WHERE isdelete != true AND previous_id =0 LIMIT ?, ?", new BeanListHandler<Question>(Question.class),startIndex,pagesize);
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public List<Question> findQuestionsByKeyWordByPage(String keywords, int startIndex, int pagesize) {
        if (keywords.contains("%")) {
            keywords = keywords.replace("%", "\\%");
        }
        if (keywords == null || "".equals(keywords)) {
            return findPageRecords(startIndex, pagesize);
        }
        try {
            String sql = "SELECT * FROM question WHERE isdelete != true AND previous_id =0 AND question_content LIKE ? ? ? limit ?,? ";
            return qr.query(sql, new BeanListHandler<Question>(Question.class),"%",keywords,"%",startIndex,pagesize);
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public int getTotalRecordsByKeyWord(String keywords){
        if (keywords.contains("%")) {
            keywords = keywords.replace("%", "\\%");
        }
        if(keywords==null || "".equals(keywords)){
            return getTotalRecords();
        }
        try {
            return  (int) qr.query("SELECT COUNT(*) FROM question WHERE question_content LIKE ? ? ? AND isdelete != true AND previous_id =0",new ScalarHandler(1),"%",keywords,"%");
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public List<Question> findQuestionsByKeyWordByPageDESC(String keywords, int startIndex, int pagesize) {
        if (keywords.contains("%")) {
            keywords = keywords.replace("%", "\\%");
        }
        System.out.println("dao-----"+keywords);
        if (keywords==null || "".equals(keywords)) {
            return findPageRecordsDESC(startIndex, pagesize);
        }
          try {
            return qr.query("SELECT * FROM question WHERE id LIKE ? ? ? AND isdelete != true AND previous_id = 0 ORDER BY id DESC LIMIT ?,?", new BeanListHandler<Question>(Question.class),"%",keywords,"%",startIndex,pagesize);
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public List<Question> findPageRecordsDESC(int startIndex, int pagesize) {
         try {
            return qr.query("SELECT * FROM question WHERE isdelete != true AND previous_id = 0 ORDER BY id DESC LIMIT ?,?", new BeanListHandler<Question>(Question.class),startIndex,pagesize);
        } catch (SQLException e) {
            throw new DBException();
        }
    }

    @Override
    public void deleteByDisplayIds(String displayIds) {
            try {
                int i = qr.update("UPDATE question SET isdelete = true  WHERE display_id in ( "+displayIds+") ");
                System.out.println("is it delete ?"+i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
