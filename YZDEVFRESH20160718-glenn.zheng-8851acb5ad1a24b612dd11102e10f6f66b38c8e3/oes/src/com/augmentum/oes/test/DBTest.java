package com.augmentum.oes.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.augmentum.oes.dao.QuestionDao;
import com.augmentum.oes.dao.UserDao;
import com.augmentum.oes.dao.impl.QuestionDaoImpl;
import com.augmentum.oes.dao.impl.UserDaoImpl;
import com.augmentum.oes.model.Question;
import com.augmentum.oes.model.User;
import com.augmentum.oes.util.DBUtil;

public class DBTest {

    private UserDao userDao = new UserDaoImpl();
    private QuestionDao questionDao = new QuestionDaoImpl();

    @Test
    public void testCreate() {
        Connection conn = DBUtil.getConn();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user(user_name, password)  VALUES('test', 'test')");
            boolean rs = stmt.execute();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetUserByPwd() {
        User user = userDao.getUserByPwd("glenn", "abc123");
        System.out.println(user.getId());
    }

    @Test
    public void testCreateQuestion(){

        for(int i = 0; i < 500; i++) {
            Question question = new Question();
            question.setDisplayId("Q000"+i);
            question.setQuestionContent("To be or not to be? "+i);
            question.setOptionOne("A");
            question.setOptionFour("D");
            question.setOptionTwo("B");
            question.setOptionThree("C");
            question.setCorrectOption("C");
            questionDao.createQuestion(question);
        }
    }
}
