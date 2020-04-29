package com.example.shiroredis.service;

import com.example.shiroredis.dao.domain.SysResources;

import java.util.List;

public interface ResourcesService {
    List<SysResources> selectAll();
}
