package com.example.kaptcha.config;

import lombok.Data;

/**
 * 背景颜色
 *
 * @author novel
 * @date 2019/9/29
 */
@Data
public class BackgroundColor {

    /**
     * 开始渐变色
     */
    private String from = "lightGray";
    /**
     * 结束渐变色
     */
    private String to = "white";

}