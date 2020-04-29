package com.example.log.dao;

import com.example.log.model.Log;

public interface LogDao {
    /**
     * 添加一个日志
     *
     * @param log 日志对象
     * @return 影响的条数
     */
    int insert(Log log);
}
