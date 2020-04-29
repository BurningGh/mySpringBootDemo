package com.example.shiroredissession.service;


import com.example.shiroredissession.entity.User;

import java.util.List;

public interface UserService {

    List<User> queryUserList();

    User queryUserByUserName(String username);

    User queryUserById(Integer id);

    void saveOneUser(User user);

    void delOneUserById(Integer id);
}
