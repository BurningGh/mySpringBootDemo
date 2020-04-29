package com.example.shiroredis.service;

import java.util.Set;

public interface SysRoleService {
    Set<String> findRoleNameByUserId(int userId);
}
