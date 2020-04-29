package com.example.shiroredis.service.impl;

import com.example.shiroredis.dao.domain.SysUser;
import com.example.shiroredis.dao.mapper.SysResourcesMapper;
import com.example.shiroredis.dao.mapper.SysUserMapper;
import com.example.shiroredis.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final SysUserMapper userMapper;

    private final SysResourcesMapper sysResourcesMapper;

    public UserServiceImpl(SysUserMapper userMapper, SysResourcesMapper sysResourcesMapper) {
        this.userMapper = userMapper;
        this.sysResourcesMapper = sysResourcesMapper;
    }

    @Override
    public SysUser getUser(SysUser user) {
        return userMapper.selectOne(user);
    }

    @Override
    public Set<String> findPermissionsByUserId(int userId) {
        Set<String> permissions = sysResourcesMapper.findRoleNameByUserId(userId);
        Set<String> result = new HashSet<>();
        for (String permission : permissions) {
            if (StringUtils.isBlank(permission)) {
                continue;
            }
            permission = StringUtils.trim(permission);
            result.addAll(Arrays.asList(permission.split("\\s*;\\s*")));
        }
        return result;
    }
}
