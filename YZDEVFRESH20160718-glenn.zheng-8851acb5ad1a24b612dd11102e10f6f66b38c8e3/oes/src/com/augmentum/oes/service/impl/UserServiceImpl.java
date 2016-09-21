package com.augmentum.oes.service.impl;

import com.augmentum.oes.dao.UserDao;
import com.augmentum.oes.exception.ParameterException;
import com.augmentum.oes.exception.ServiceException;
import com.augmentum.oes.model.User;
import com.augmentum.oes.service.UserService;
import com.augmentum.oes.util.StringUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private ParameterException paramException = new ParameterException();
    private ServiceException serviceException = new ServiceException(1000, "username or password does not exist!");

    @Override
    public User login (String username, String password) throws ParameterException, ServiceException {

        if (StringUtil.isEmpty(username)) {
            paramException.addErrorFields("userNameError", "userName is required!!");
        }
        if (StringUtil.isEmpty(password)) {
            paramException.addErrorFields("passwordError", "password is required!!");
        }

        if (!paramException.isErrorField()) {
            throw paramException;
        }

        User user = userDao.getUserByPwd(username, password);

        if (user == null) {
            throw serviceException;
        }
        return user;
    }

    @Override
    public void changePassword(String id, String password) throws ParameterException {
        if ( StringUtil.isEmpty(password)) {
            paramException.addErrorFields("passwordError", "password is required!!");
        }
        if (!paramException.isErrorField()) {
            throw paramException;
        }
        userDao.changePassword(id, password);
    }
}
