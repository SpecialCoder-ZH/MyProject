package com.augmentum.oes.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.jdbc.core.JdbcTemplate;

import com.augmentum.oes.dao.UserDao;
import com.augmentum.oes.exception.DBException;
import com.augmentum.oes.model.User;
import com.augmentum.oes.util.StringUtil;
import com.augmentum.oes.util.TransactionUtil;

public class UserDaoImpl implements UserDao {

    private QueryRunner qr = new QueryRunner(TransactionUtil.getDataSource());

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    }

    @Override
    public User getUserById(String id) {
        if (StringUtil.isEmpty(id)) {
            return null;
        }
        try {
            return qr.query("SELECT * FROM user WHERE userId=?", new BeanHandler<User>(User.class));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<User> findUsers() {
        try {
            return qr.query("SELECT * FROM user", new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public User getUserByPwd(String username, String password) {
        User user = null;
        try {
            user = qr.query("SELECT * FROM user WHERE user_name=? AND password = ?", new BeanHandler<User>(User.class),username,password);
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return user;
    }

    @Override
    public void createUser(User user) {
    }

    @Override
    public void deleteUserById(String id) {
    }

    @Override
    public void updateUser(User user) {
    }

    @Override
    public void changePassword(String id, String password) {
        // TODO Auto-generated method stub

    }
}
