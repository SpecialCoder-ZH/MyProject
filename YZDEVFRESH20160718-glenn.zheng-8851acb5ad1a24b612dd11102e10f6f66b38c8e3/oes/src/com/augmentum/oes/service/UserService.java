package com.augmentum.oes.service;

import com.augmentum.oes.exception.ParameterException;
import com.augmentum.oes.exception.ServiceException;
import com.augmentum.oes.model.User;

public interface UserService {

    User login (String username, String password) throws ParameterException, ServiceException;

    void changePassword(String id, String password) throws ParameterException;
}
