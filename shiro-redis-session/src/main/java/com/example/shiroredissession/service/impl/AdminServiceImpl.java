package com.example.shiroredissession.service.impl;

import com.example.shiroredissession.dao.AuthDao;
import com.example.shiroredissession.dao.RoleDao;
import com.example.shiroredissession.entity.Auth;
import com.example.shiroredissession.entity.Role;
import com.example.shiroredissession.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final RoleDao roleDao;
    private final AuthDao authDao;

    public AdminServiceImpl(RoleDao roleDao, AuthDao authDao) {
        this.roleDao = roleDao;
        this.authDao = authDao;
    }

    @Override
    public List<Role> queryRoleList() {
        return roleDao.queryRoleList();
    }

    @Override
    public void deleteOneRoleById(Integer id) {
        roleDao.deleteOneRoleById(id);
    }

    @Override
    public List<Auth> queryAuthList() {
        return authDao.queryAuthList();
    }

    @Override
    public void deleteOneAuthById(Integer id) {
        authDao.deleteOneAuthById(id);
    }
}
