package com.example.kaptcha.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 验证码属性配置
 *
 * @author novel
 * @date 2019/9/28
 */
@ConfigurationProperties(prefix = KaptchaProperties.PREFIX)
@Data
public class KaptchaProperties {
    public static final String PREFIX = "kaptcha";
    /**
     * 验证码类型
     */
    private KaptchaType type = KaptchaType.DEFAULT;

    /**
     * 宽度
     */
    private Integer width = 160;
    /**
     * 高度
     */
    private Integer height = 60;

    /**
     * 是否区分大小写
     */
    private boolean caseSensitivity = false;
    /**
     * 内容
     */
    @NestedConfigurationProperty
    private Content content = new Content();
    /**
     * 背景色
     */
    @NestedConfigurationProperty
    private BackgroundColor backgroundColor = new BackgroundColor();
    /**
     * 字体
     */
    @NestedConfigurationProperty
    private Font font = new Font();
    /**
     * 边框
     */
    @NestedConfigurationProperty
    private Border border = new Border();
    /**
     * 干扰
     */
    @NestedConfigurationProperty
    private Noise noise = new Noise();
    /**
     * 图片样式
     */
    @NestedConfigurationProperty
    private Obscurificator obscurificator = new Obscurificator();
}





