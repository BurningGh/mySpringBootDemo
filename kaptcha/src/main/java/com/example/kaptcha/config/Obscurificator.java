package com.example.kaptcha.config;

import lombok.Data;

/**
 * 图片样式
 *
 * @author novel
 * @date 2019/9/29
 */
@Data
public class Obscurificator {
    /**
     * 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy 阴影com.google.code.kaptcha.impl.ShadowGimpy
     */
    private String impl = "com.google.code.kaptcha.impl.ShadowGimpy";
}
