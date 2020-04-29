package com.example.shiroredissession.service;

import com.example.shiroredissession.entity.Auth;

import java.util.List;

public interface AuthService {

    List<Auth> queryAuthList();

    void deleteOneAuthById(Integer id);
}
