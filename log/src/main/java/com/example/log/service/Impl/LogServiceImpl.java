package com.example.log.service.Impl;

import com.example.log.dao.LogDao;
import com.example.log.model.Log;
import com.example.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    public boolean add(Log log) {

        if (log == null) {
            throw new RuntimeException("日志对象不能为空！");
        }

        return logDao.insert(log) > 0 ? true : false;
    }
}
