package com.example.shiroredissession.dao;

import com.example.shiroredissession.entity.Auth;
import com.example.shiroredissession.entity.Role;
import com.example.shiroredissession.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> queryAllUsers();

    User queryUserByUsername(String username);

    User queryUserById(Integer id);

    Integer queryRoleIdByUserId(Integer id);// user_role

    Role queryRoleByRoleId(Integer id);

    List<Integer> queryAuthIdByRoleId(Integer id);// role_auth

    Auth queryAuthByAuthId(Integer id);

    void saveOneUser(User user);

    void delOneUserById(Integer id);
}
