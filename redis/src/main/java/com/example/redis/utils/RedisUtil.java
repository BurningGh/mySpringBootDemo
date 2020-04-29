package com.example.redis.utils;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
public final class RedisUtil {

    private final RedisTemplate<Object, Object> redisTemplate;
    private ValueOperations<Object, Object> valueOps;
    private ListOperations<Object, Object> listOps;
    private SetOperations<Object, Object> setOps;
    private ZSetOperations<Object, Object> zsetOps;
    private HashOperations<Object, Object, Object> hashOps;

    public RedisUtil(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOps = this.redisTemplate.opsForValue();
        this.listOps = this.redisTemplate.opsForList();
        this.setOps = this.redisTemplate.opsForSet();
        this.zsetOps = this.redisTemplate.opsForZSet();
        this.hashOps = this.redisTemplate.opsForHash();
    }

    public void setValue(String key, Object value) {
        valueOps.set(key, value);
    }

    public Object getValue(String key) {
        return valueOps.get(key);
    }

    //返回值为设置成功的value数
    public Long setSet(String key, String... value) {
        return setOps.add(key, value);
    }

    //返回值为redis中键值为key的value的Set集合
    public Set<Object> getSetMembers(String key) {
        return setOps.members(key);
    }

    public Boolean setZSet(String key, String value, double score) {
        return zsetOps.add(key, value, score);
    }

    public Double getZSetScore(String key, String value) {
        return zsetOps.score(key, value);
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    public void deleteKeys(Collection<String> keys) {
        redisTemplate.delete(keys);
    }
}