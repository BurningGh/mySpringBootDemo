package com.example.shiroredissession.service;

import com.example.shiroredissession.entity.Auth;
import com.example.shiroredissession.entity.Role;

import java.util.List;

public interface AdminService {

    List<Role> queryRoleList();

    void deleteOneRoleById(Integer id);

    List<Auth> queryAuthList();

    void deleteOneAuthById(Integer id);
}
