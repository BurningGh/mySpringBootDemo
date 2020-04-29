package com.example.kaptcha.cache.manager;

import com.example.kaptcha.cache.KaptchaCache;
import com.example.kaptcha.cache.impl.RedisKaptchaCache;
import com.example.kaptcha.exception.KaptchaCacheException;

/**
 * 默认缓存管理器
 *
 * @author novel
 * @date 2019/12/2
 */
public class RedisKaptchaCacheManagerImpl extends AbstractKaptchaCacheManager {

    private final RedisKaptchaCache redisKaptchaCache;

    public RedisKaptchaCacheManagerImpl(RedisKaptchaCache redisKaptchaCache) {
        this.redisKaptchaCache = redisKaptchaCache;
    }

    @Override
    KaptchaCache createCache(String key) throws KaptchaCacheException {
        redisKaptchaCache.setPrefix(key);
        return redisKaptchaCache;
    }
}
