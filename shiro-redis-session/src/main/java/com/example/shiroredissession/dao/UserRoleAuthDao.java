package com.example.shiroredissession.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleAuthDao {

    void delRole(Integer user_id, Integer role_id);

    void delAuth(Integer role_id, Integer auth_id);

    void saveUserRole(Integer user_id, Integer role_id);

    void saveRoleAuth(Integer role_id, Integer auth_id);
}
