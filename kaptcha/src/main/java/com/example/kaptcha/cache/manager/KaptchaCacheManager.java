package com.example.kaptcha.cache.manager;


import com.example.kaptcha.cache.KaptchaCache;
import com.example.kaptcha.exception.KaptchaCacheException;

/**
 * 验证码缓存管理器
 *
 * @author novel
 * @date 2019/12/2
 */
public interface KaptchaCacheManager {
    /**
     * 获取一个缓存对象
     *
     * @param key 缓存对象的key
     * @return 验证码缓存对象
     * @throws KaptchaCacheException 验证码缓存异常
     */
    KaptchaCache getCache(String key) throws KaptchaCacheException;
}
