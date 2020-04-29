package com.example.kaptcha.adapter;

import com.example.kaptcha.KaptchaCode;
import com.example.kaptcha.cache.manager.KaptchaCacheManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;

/**
 * 验证码产生适配器
 *
 * @author novel
 * @date 2019/9/28
 */
public interface Kaptcha {

    /**
     * 渲染验证码
     *
     * @return 验证码内容图片
     */
    BufferedImage render(@NotNull String key);

    /**
     * 自动生成验证码
     *
     * @return 验证码对象
     */
    KaptchaCode render();

    /**
     * 自动写出一个图片流验证码
     *
     * @return 验证码key
     */
    String writeImageCode(HttpServletResponse response);

    /**
     * 自动产生一个base64形式验证码
     *
     * @return 验证码对象
     */
    KaptchaCode writeBase64Code();

    /**
     * 校对验证码
     *
     * @param key  验证码key
     * @param code 需要验证的字符串
     * @return 是否验证成功
     */
    boolean validate(@NotNull String key, String code);

    /**
     * 根据缓存的验证码key获取对应验证码
     *
     * @param key 验证码key
     * @return 验证码
     */
    String getKaptchaCode(@NotNull String key);

    /**
     * 设置缓存管理器
     *
     * @param kaptchaCacheManager 缓存管理器
     */
    void setCacheManager(KaptchaCacheManager kaptchaCacheManager);
}
