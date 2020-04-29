package com.example.kaptcha.config;

import lombok.Data;

/**
 * 字体
 *
 * @author novel
 * @date 2019/9/29
 */
@Data
public class Font {
    /**
     * 名称  每个字体 "," 隔开
     */
    private String names = "Arial,Courie";
    /**
     * 颜色
     */
    private String color = "blue";
    /**
     * 大小
     */
    private Integer size = 35;

}