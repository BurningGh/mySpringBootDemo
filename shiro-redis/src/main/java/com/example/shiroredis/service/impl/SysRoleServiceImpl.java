package com.example.shiroredis.service.impl;

import com.example.shiroredis.dao.mapper.SysRoleMapper;
import com.example.shiroredis.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    public Set<String> findRoleNameByUserId(int userId) {
        return sysRoleMapper.findRoleNameByUserId(userId);
    }
}
