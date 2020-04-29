package com.example.kaptcha.cache;

import com.example.kaptcha.cache.config.CacheType;

/**
 * 保存验证码接口
 *
 * @author novel
 * @date 2019/9/28
 */
public interface KaptchaCache {
    /**
     * 初始化缓存对象
     */
    void init();

    /**
     * 销毁缓存对象
     */
    void destroy();

    /**
     * 获取缓存类型
     *
     * @return 缓存类型
     */
    CacheType getCacheType();

    /**
     * 保存验证码
     *
     * @param key  验证码key
     * @param code 验证码
     */
    void saveKaptcha(String key, String code);

    /**
     * 获取验证码
     *
     * @param key 验证码key
     * @return 验证码
     */
    String getKaptcha(String key);

    /**
     * 删除验证码
     *
     * @param key 验证码key
     * @return
     */
    void removeKaptcha(String key);
}
