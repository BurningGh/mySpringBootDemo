package com.example.shiroredissession.service.impl;


import com.example.shiroredissession.dao.AuthDao;
import com.example.shiroredissession.entity.Auth;
import com.example.shiroredissession.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AuthDao authDao;

    public AuthServiceImpl(AuthDao authDao) {
        this.authDao = authDao;
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
