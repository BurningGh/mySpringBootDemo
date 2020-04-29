package com.example.shiroredissession.service;

import com.example.shiroredissession.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> queryRoleList();

    void deleteOneRoleById(Integer id);
}
