package com.example.log.service;

import com.example.log.model.Log;

public interface LogService {
    /**
     * 新增一个日志
     * @param log
     * @return
     */
    public boolean add(Log log);
}
