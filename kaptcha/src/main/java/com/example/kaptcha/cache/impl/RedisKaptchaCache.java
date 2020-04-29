package com.example.kaptcha.cache.impl;

import com.example.kaptcha.cache.AbstractKaptchaCache;
import com.example.kaptcha.cache.config.KaptchaCacheProperties;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis实现kaptcha缓存
 *
 * @author novel
 * @date 2019/12/2
 */
public class RedisKaptchaCache extends AbstractKaptchaCache {
    private final StringRedisTemplate redisTemplate;


    public RedisKaptchaCache(StringRedisTemplate redisTemplate, KaptchaCacheProperties kaptchaCacheProperties) {
        super(kaptchaCacheProperties);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveKaptcha(String key, String code) {
        redisTemplate.opsForValue().set(getPrefix() + ":" + key, code, getKaptchaCacheProperties().getCacheTimeOut(), TimeUnit.MILLISECONDS);
    }

    @Override
    public String getKaptcha(String key) {
        return redisTemplate.opsForValue().get(getPrefix() + ":" + key);
    }

    @Override
    public void removeKaptcha(String key) {
        redisTemplate.delete(getPrefix() + ":" + key);
    }
}
