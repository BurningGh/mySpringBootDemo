package com.example.readenv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置类
 *
 * @author lz
 * @date 2019/6/5
 */
@Component
public class Global {
    private static final Logger log = LoggerFactory.getLogger(Global.class);

    private final Environment environment;
    private static Environment env;

    /**
     * 保存全局属性值
     */
    private static Map<String, Object> map = new HashMap<>();

    @Autowired
    private Global(Environment env) {
        this.environment = env;
    }

    @PostConstruct
    public void init() {
        env = environment;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        Object o = map.get(key);
        String value = null;
        if (o instanceof String) {
            value = (String) o;
        }
        if (StringUtils.isEmpty(value)) {
            try {
                value = env.getProperty(key);

                map.put(key, StringUtils.isNotEmpty(value) && !"null".equals(value) ? value : StringUtils.EMPTY);
            } catch (Exception e) {
                log.error("获取全局配置异常 {}", key);
            }
        }
        return StringUtils.isNotEmpty(value) && !"null".equals(value) ? value : StringUtils.EMPTY;
    }

    /**
     * 获取配置
     *
     * @param key   配置key
     * @param clazz 返回值类型
     * @param <T>   返回值泛型
     * @return 配置值
     */
    @Nullable
    @SuppressWarnings("all")
    public static <T> T getConfig(String key, Class<T> clazz) {
        T value = (T) map.get(key);
        if (value == null) {
            try {
                value = env.getProperty(key, clazz);
                map.put(key, value);
            } catch (Exception e) {
                log.error("获取全局配置异常 {}", key);
            }
        }
        return value;
    }

    /**
     * 获取配置
     *
     * @param key          配置key
     * @param clazz        返回值类型
     * @param defaultValue 默认值
     * @param <T>          返回值泛型
     * @return 配置值
     */
    @SuppressWarnings("all")
    public static <T> T getConfig(String key, Class<T> clazz, T defaultValue) {
        Object o = map.get(key);
        T value = (T) map.get(key);
        if (value == null) {
            try {
                value = env.getProperty(key, clazz, defaultValue);
                map.put(key, value);
            } catch (Exception e) {
                log.error("获取全局配置异常 {}", key);
            }
        }
        return value;
    }
}
