package com.example.kaptcha.config;

import lombok.Data;

/**
 * 边框
 *
 * @author novel
 * @date 2019/9/29
 */
@Data
public class Border {

    /**
     * 是否开启
     */
    private Boolean enabled = true;
    /**
     * 颜色
     */
    private String color = "105,179,90";
    /**
     * 厚度
     */
    private Integer thickness = 1;

}
