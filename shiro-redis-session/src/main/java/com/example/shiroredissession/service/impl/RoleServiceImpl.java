package com.example.shiroredissession.service.impl;

import com.example.shiroredissession.dao.RoleDao;
import com.example.shiroredissession.entity.Role;
import com.example.shiroredissession.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> queryRoleList() {
        return roleDao.queryRoleList();
    }

    @Override
    public void deleteOneRoleById(Integer id) {
        roleDao.deleteOneRoleById(id);
    }
}
