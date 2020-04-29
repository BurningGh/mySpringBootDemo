package com.example.redis5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;

@SpringBootApplication
public class Redis5Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Redis5Application.class, args);
        StringRedisTemplate redisTemplate = run.getBean("stringRedisTemplate", StringRedisTemplate.class);
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set("222", "11111");

        System.out.println(operations.get("222"));
    }

}
