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
 * 默认随机验证码实现类
 *
 * @author novel
 * @date 2019/12/2
 */
public class DefaultKaptchaImpl extends AbstractKaptchaImpl {

    public DefaultKaptchaImpl(DefaultKaptcha kaptcha, KaptchaProperties kaptchaProperties) {
        super(kaptcha, kaptchaProperties);
    }

    @Override
    public BufferedImage render(@NotNull String key) {
        String capText = kaptcha.createText();
        KaptchaCache cache = kaptchaCacheManager.getCache((KaptchaProperties.PREFIX + "_" + this.kaptchaProperties.getType()).toUpperCase());
        cache.saveKaptcha(key, capText);
        return kaptcha.createImage(capText);
    }

    @Override
    public boolean validate(@NotNull String key, String code) {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(code.trim())) {
            //需要验证的验证码不存在，用户未输入
            throw new KaptchaNotFoundException();
        }
        String kaptchaCode = getKaptchaCode(key);

        if (kaptchaProperties.isCaseSensitivity()) {
            //区分大小写
            return kaptchaCode.equals(code);
        } else {
            //不区分大小写
            return kaptchaCode.equalsIgnoreCase(code);
        }
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
