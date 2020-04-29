package com.example.shiroredissession.service.impl;


import com.example.shiroredissession.dao.UserRoleAuthDao;
import com.example.shiroredissession.service.UserRoleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleAuthServiceImpl implements UserRoleAuthService {

    private final UserRoleAuthDao userRoleAuthDao;

    public UserRoleAuthServiceImpl(UserRoleAuthDao userRoleAuthDao) {
        this.userRoleAuthDao = userRoleAuthDao;
    }

    @Override
    public void delRole(Integer user_id, Integer role_id) {
        userRoleAuthDao.delRole(user_id, role_id);
    }

    @Override
    public void delAuth(Integer role_id, Integer auth_id) {
        userRoleAuthDao.delAuth(role_id, auth_id);
    }

    @Override
    public void saveUserRole(Integer user_id, Integer role_id) {
        userRoleAuthDao.saveUserRole(user_id, role_id);
    }

    @Override
    public void saveRoleAuth(Integer role_id, Integer auth_id) {
        userRoleAuthDao.saveRoleAuth(role_id, auth_id);
    }
}
