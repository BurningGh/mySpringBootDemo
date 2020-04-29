package com.example.shiroredissession.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Auth implements Serializable {
    public Integer id;
    public String auth_name;
}
