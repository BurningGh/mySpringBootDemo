package com.example.log.model;

import lombok.Data;

@Data
public class Log {
    /**
     * id
     */
    private Integer id;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 模块
     */
    private String title;
    /**
     * 具体方法
     */
    private String action;
    /**
     * 请求的url
     */
    private String url;
    /**
     * 方法名
     */
    private String method;
    /**
     * 请求的参数
     */
    private String param;
}
