package com.example.kaptcha.adapter.impl;

import com.example.kaptcha.adapter.AbstractKaptchaImpl;
import com.example.kaptcha.cache.KaptchaCache;
import com.example.kaptcha.config.KaptchaProperties;
import com.example.kaptcha.exception.KaptchaNotFoundException;
import com.example.kaptcha.exception.KaptchaTimeoutException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;

/**
 * 数学计算验证码组件实现类
 *
 * @author novel
 * @date 2019/9/28
 */
public class MathKaptchaImpl extends AbstractKaptchaImpl {

    public MathKaptchaImpl(DefaultKaptcha kaptcha, KaptchaProperties kaptchaProperties) {
        super(kaptcha, kaptchaProperties);
    }

    @Override
    public BufferedImage render(String key) {
        String capText = kaptcha.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        KaptchaCache cache = kaptchaCacheManager.getCache((KaptchaProperties.PREFIX + "_" + this.kaptchaProperties.getType()).toUpperCase());
        cache.saveKaptcha(key, code);
        return kaptcha.createImage(capStr);
    }

    @Override
    public boolean validate(@NotNull String key, String code) {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(code.trim())) {
            //需要验证的验证码不存在，用户未输入
            throw new KaptchaNotFoundException();
        }

        String kaptchaCode = getKaptchaCode(key);
        return kaptchaCode.equalsIgnoreCase(code);
    }

    @Override
    public String getKaptchaCode(@NotNull String key) {
        KaptchaCache cache = kaptchaCacheManager.getCache((KaptchaProperties.PREFIX + "_" + this.kaptchaProperties.getType()).toUpperCase());
        String kaptcha = cache.getKaptcha(key);
        cache.removeKaptcha(key);
        if (StringUtils.isEmpty(kaptcha)) {
            //验证码不存在，已经过期
            throw new KaptchaTimeoutException();
        }
        return kaptcha;
    }
}
