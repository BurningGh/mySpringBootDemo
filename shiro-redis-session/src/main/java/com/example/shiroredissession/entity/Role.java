package com.example.shiroredissession.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {

    public Integer id;

    public String role_name;

    // 角色可有多个权限、对应role_auth
    public List<Auth> authList;

}
