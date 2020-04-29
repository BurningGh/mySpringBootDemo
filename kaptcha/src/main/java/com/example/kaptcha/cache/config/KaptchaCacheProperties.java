package com.example.kaptcha.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 验证码存储器属性配置
 *
 * @author novel
 * @date 2019/9/28
 */
@ConfigurationProperties(prefix = KaptchaCacheProperties.PREFIX)
@Data
public class KaptchaCacheProperties {
    public static final String PREFIX = "kaptcha.cache";
    /**
     * 验证码默认超时时间为10分钟
     */
    private long cacheTimeOut = 10 * 60 * 1000L;
    /**
     * 缓存存储类型
     */
    private CacheType type = CacheType.SESSION;
}
