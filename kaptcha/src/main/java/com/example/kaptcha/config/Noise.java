package com.example.kaptcha.config;

import lombok.Data;

/**
 * 干扰
 *
 * @author novel
 * @date 2019/9/29
 */
@Data
public class Noise {
    /**
     * 颜色
     */
    private String color = "white";
    /**
     * 干扰实现类
     */
    private String impl = "com.google.code.kaptcha.impl.NoNoise";
}
