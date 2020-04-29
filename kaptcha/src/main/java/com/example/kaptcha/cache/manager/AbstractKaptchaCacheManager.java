package com.example.kaptcha.cache.manager;

import com.example.kaptcha.cache.KaptchaCache;
import com.example.kaptcha.exception.KaptchaCacheException;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 验证码缓存管理器实现类
 *
 * @author novel
 * @date 2019/12/2
 */
public abstract class AbstractKaptchaCacheManager implements KaptchaCacheManager {
    private final ConcurrentMap<String, KaptchaCache> caches = new ConcurrentHashMap<>();

    @Override
    public KaptchaCache getCache(String key) throws KaptchaCacheException {
        if (!StringUtils.hasText(key)) {
            throw new KaptchaCacheException("Cache key cannot be null or empty.");
        } else {
            KaptchaCache kaptchaCache = this.caches.get(key);
            if (kaptchaCache == null) {
                kaptchaCache = this.createCache(key);
                KaptchaCache existing = this.caches.putIfAbsent(key, kaptchaCache);
                if (existing != null) {
                    kaptchaCache = existing;
                }
            }
            return kaptchaCache;
        }
    }

    /**
     * 创建一个验证码缓存对象
     *
     * @param key 缓存对象的key
     * @return 验证码缓存对象
     * @throws KaptchaCacheException 验证码缓存异常
     */
    abstract KaptchaCache createCache(String key) throws KaptchaCacheException;

    /**
     * 销毁一个验证码缓存对象
     *
     * @throws KaptchaCacheException 验证码缓存异常
     */
    public void destroy() throws KaptchaCacheException {
        if (!this.caches.isEmpty()) {
            for (KaptchaCache kaptchaCache : this.caches.values()) {
                if (kaptchaCache != null) {
                    kaptchaCache.destroy();
                }
            }
            this.caches.clear();
        }
    }
}
