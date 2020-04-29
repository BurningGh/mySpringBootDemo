package com.example.shiroredis.service.impl;

import com.example.shiroredis.dao.domain.SysResources;
import com.example.shiroredis.dao.mapper.SysResourcesMapper;
import com.example.shiroredis.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    private final SysResourcesMapper sysResourcesMapper;

    public ResourcesServiceImpl(SysResourcesMapper sysResourcesMapper) {
        this.sysResourcesMapper = sysResourcesMapper;
    }

    @Override
    public List<SysResources> selectAll() {
        List<SysResources> resourcesList = sysResourcesMapper.selectAll();
        return resourcesList;
    }
}
