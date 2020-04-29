package com.example.shiroredissession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.shiroredissession.dao")
public class ShiroRedisSessionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroRedisSessionApplication.class, args);
    }
}
