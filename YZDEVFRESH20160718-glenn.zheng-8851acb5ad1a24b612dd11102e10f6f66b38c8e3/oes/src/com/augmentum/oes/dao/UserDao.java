package com.augmentum.oes.dao;

import java.util.List;

import com.augmentum.oes.model.User;

public interface UserDao {
    void createUser(User user);
    void deleteUserById(String id);
    void updateUser(User user);
    User getUserById(String id);
    List<User> findUsers();
    User getUserByPwd(String username, String password);
    void changePassword(String id, String password);
}
