package com.example.mybatisplus;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dengwenjian
 * @date 2020/12/21
 */
@Slf4j
@RestController
@RequestMapping("hello")
public class HelloController {

    @NacosInjected
    private ConfigService configService;

    @Resource
    private StudentProperty studentProperty;

    private final Executor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()
            , Runtime.getRuntime().availableProcessors() * 2
            , 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
            (Runnable r) -> new Thread(r, "thread_pool")
            , new ThreadPoolExecutor.AbortPolicy());

    /**
     * 必须在NacosPropertySource中配置了自动刷新为true，这里的autoRefreshed才能生效
     */
    @NacosValue(value = "${name}", autoRefreshed = true)
    private String name;

    /**
     * 测试直接@NacosValue获取配置
     */
    @GetMapping("name")
    public String name() {
        return "hello, " + name;
    }

    /**
     * 测试注解@NacosConfigurationProperties方式获取配置
     */
    @GetMapping("student")
    public String student() {
        return studentProperty.toString();
    }

    /**
     * 测试configService方式获取配置
     */
    @GetMapping("config")
    public String config() throws Exception {
        ConfigWrapper configWrapper = new ConfigWrapper();
        configWrapper.setConfig(
                //getConfigAndSignListener实现获取并动态刷新
                configService.getConfigAndSignListener("test_config", "DEFAULT_GROUP", 1000L, new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return executor;
                    }

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        configWrapper.setConfig(configInfo);
                    }
                })
        );
        return configWrapper.getConfig();
    }

    @Data
    public static class ConfigWrapper {
        private String config;
    }
}
