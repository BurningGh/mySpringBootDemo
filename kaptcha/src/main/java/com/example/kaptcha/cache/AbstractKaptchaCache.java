package com.example.kaptcha.cache;

import com.example.kaptcha.cache.config.CacheType;
import com.example.kaptcha.cache.config.KaptchaCacheProperties;

/**
 * 验证码缓存抽象处理类
 *
 * @author novel
 * @date 2019/12/23
 */
public abstract class AbstractKaptchaCache implements KaptchaCache {
    private final KaptchaCacheProperties kaptchaCacheProperties;
    private String prefix;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    protected String getPrefix() {
        return prefix;
    }

    protected KaptchaCacheProperties getKaptchaCacheProperties() {
        return kaptchaCacheProperties;
    }

    public AbstractKaptchaCache(KaptchaCacheProperties kaptchaCacheProperties) {
        this.kaptchaCacheProperties = kaptchaCacheProperties;
    }

    @Override
    public CacheType getCacheType() {
        return kaptchaCacheProperties.getType();
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {
    }
}
