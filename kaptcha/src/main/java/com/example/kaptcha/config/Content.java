package com.example.kaptcha.config;

import lombok.Data;

/**
 * 内容
 *
 * @author novel
 * @date 2019/9/29
 */
@Data
public class Content {

    /**
     * 内容源
     */
    private String source = "abcdefghjklmnopqrstuvwxyz23456789";
    /**
     * 内容长度
     */
    private Integer length = 6;
    /**
     * 内容间隔
     */
    private Integer space = 3;

}