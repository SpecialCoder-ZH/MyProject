package com.augmentum.oes.dao.mybatis_impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.augmentum.oes.dao.UserDao;
import com.augmentum.oes.exception.DBException;
import com.augmentum.oes.model.User;
import com.augmentum.oes.util.StringUtil;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{

    private final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private static final String CLASS_NAME = User.class.getName();
    private static final String SQL_ID_USER_GET_USER_BY_NAME = ".getUserById";
    private static final String SQL_ID_USER_FIND_USERS = ".findUsers";
    private static final String SQL_ID_GET_USER_BY_PWD = ".getUserByPwd";
    private static final String SQL_ID_CHANGE_PWD = ".changePassword";

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
    public User getUserById(String id) {

        if (StringUtil.isEmpty(id)) {
            return null;
        }
        try {
            return getSqlSession().selectOne(CLASS_NAME + SQL_ID_USER_GET_USER_BY_NAME, id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public List<User> findUsers() {
        try {
            return getSqlSession().selectList(CLASS_NAME + SQL_ID_USER_FIND_USERS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public User getUserByPwd(String username, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        try {
            return getSqlSession().selectOne(CLASS_NAME + SQL_ID_GET_USER_BY_PWD,params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DBException();
        }
    }

    @Override
    public void changePassword(String id, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        params.put("password", password);
        try {
            getSqlSession().selectOne(CLASS_NAME + SQL_ID_CHANGE_PWD, params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
