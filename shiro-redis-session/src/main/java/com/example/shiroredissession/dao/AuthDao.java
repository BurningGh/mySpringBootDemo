package com.example.shiroredissession.dao;

import com.example.shiroredissession.entity.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthDao {

    List<Auth> queryAuthList();

    void deleteOneAuthById(Integer id);
}
