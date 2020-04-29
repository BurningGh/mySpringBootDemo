package com.example.shiroredissession.dao;

import com.example.shiroredissession.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {

    List<Role> queryRoleList();

    void deleteOneRoleById(Integer id);
}
