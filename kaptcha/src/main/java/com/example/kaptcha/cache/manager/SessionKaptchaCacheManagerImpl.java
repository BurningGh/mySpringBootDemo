package com.example.kaptcha.cache.manager;

import com.example.kaptcha.cache.KaptchaCache;
import com.example.kaptcha.cache.impl.SessionKaptchaCache;
import com.example.kaptcha.exception.KaptchaCacheException;

/**
 * 默认缓存管理器
 *
 * @author novel
 * @date 2019/12/2
 */
public class SessionKaptchaCacheManagerImpl extends AbstractKaptchaCacheManager {

    private final SessionKaptchaCache sessionKaptchaCache;

    public SessionKaptchaCacheManagerImpl(SessionKaptchaCache sessionKaptchaCache) {
        this.sessionKaptchaCache = sessionKaptchaCache;
    }

    @Override
    KaptchaCache createCache(String key) throws KaptchaCacheException {
        sessionKaptchaCache.setPrefix(key);
        return sessionKaptchaCache;
    }
}
