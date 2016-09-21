package com.augmentum.oes.dao.mybatis_impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.augmentum.oes.dao.QuestionDao;
import com.augmentum.oes.exception.DBException;
import com.augmentum.oes.model.Question;
import com.augmentum.oes.util.ReplaceCharUtil;
import com.augmentum.oes.util.StringUtil;

public class QuestionDaoImpl extends SqlSessionDaoSupport implements QuestionDao {

    private final Logger logger = Logger.getLogger(QuestionDaoImpl.class);
    private static final String CLASS_NAME = Question.class.getName();
    protected static String SQL_ID_ADD = ".createQuestion";
    protected static String SQL_ID_DELETE = ".deleteQuestionById";
    protected static String SQL_ID_GET_BY_ID = ".getQuestionById";
    protected static String SQL_ID_FIND_ALL = ".findQuestions";
    protected static String SQL_ID_FIND_BY_KEYWORDS = ".findQuestionsByKeyWord";
    protected static String SQL_ID_GET_COUNT = ".getTotalRecords";
    protected static String SQL_ID_FIND_PAGE_RECORDS = ".findPageRecords";
    protected static String SQL_ID_BY_KEYW = ".findQuestionsByKeyWordByPage";
    protected static String SQL_ID_BY_KEYW_DESC = ".findQuestionsByKeyWordByPageDESC";
    protected static String SQL_ID_GET_COUNT_KEYWORD = ".getTotalRecordsByKeyWord";
    protected static String SQL_ID_Find_COUNT_DESC = ".findPageRecordsDESC";
    protected static String SQL_ID_DELETE_BY_ID = ".deleteByDisplayIds";
    protected static String SQL_ID_UPDATE_QUES = ".updateQuestion";

    @Override
    public Integer createQuestion(Question question) {
        try {
            getSqlSession().insert(CLASS_NAME + SQL_ID_ADD, question);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
        return question.getId();
    }

    @Override
    public void deleteQuestionById(String display_id) {
        try {
            getSqlSession().delete(CLASS_NAME + SQL_ID_DELETE, display_id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public void updateQuestion(Question question) {
        try {
            getSqlSession().insert(CLASS_NAME + SQL_ID_UPDATE_QUES, question);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
           e.printStackTrace();
        }
    }

    @Override
    public Question getQuestionById(String id) {
        try {
            return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_BY_ID, id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Question> findQuestions() {
        try {
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_ALL);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Question> findQuestionsByKeyWord(String keywords) {
        try {
            keywords = ReplaceCharUtil.replace(keywords);
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_BY_KEYWORDS, keywords);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public int getTotalRecords() {
        try {
            return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_COUNT);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Question> findPageRecords(int startIndex, int pagesize) {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("startIndex", startIndex);
        params.put("pagesize", pagesize);
        try {
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_FIND_PAGE_RECORDS, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Question> findQuestionsByKeyWordByPage(String keywords, int startIndex, int pagesize) {
        keywords = ReplaceCharUtil.replace(keywords);
        if (StringUtil.isEmpty(keywords)) {
            return findPageRecords(startIndex, pagesize);
        }
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("keywords", keywords);
            params.put("startIndex", String.valueOf(startIndex));
            params.put("pagesize", String.valueOf(pagesize));
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_BY_KEYW, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public int getTotalRecordsByKeyWord(String keywords) {
        keywords = ReplaceCharUtil.replace(keywords);
        if(StringUtil.isEmpty(keywords)) {
            return getTotalRecords();
        }
        try {
            return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_COUNT_KEYWORD, keywords);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Question> findQuestionsByKeyWordByPageDESC(String keywords, int startIndex, int pagesize) {
        keywords = ReplaceCharUtil.replace(keywords);
        if (StringUtil.isEmpty(keywords)) {
            return findPageRecordsDESC(startIndex, pagesize);
        }
          try {
              Map<String, String> params = new HashMap<String, String>();
              params.put("keywords", keywords);
              params.put("startIndex", String.valueOf(startIndex));
              params.put("pagesize", String.valueOf(pagesize));
              return getSqlSession().selectList(CLASS_NAME + SQL_ID_BY_KEYW_DESC, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<Question> findPageRecordsDESC(int startIndex, int pagesize) {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("startIndex", startIndex);
        params.put("pagesize", pagesize);
         try {
             return getSqlSession().selectList(CLASS_NAME + SQL_ID_Find_COUNT_DESC, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public void deleteByDisplayIds(String displayIds) {
        String[] arr = displayIds.split(",");
            try {
                int i = getSqlSession().update(CLASS_NAME + SQL_ID_DELETE_BY_ID, arr);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new DBException();
            }
    }
}
