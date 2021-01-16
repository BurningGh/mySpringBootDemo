package com.example.mybatisplus;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dengwenjian
 */
@SpringBootApplication
@Slf4j
@NacosPropertySource(dataId = "iot-base.property", autoRefreshed = true)
public class NacosExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosExampleApplication.class, args);
        log.info("NacosExampleApplication start success");
    }
}
