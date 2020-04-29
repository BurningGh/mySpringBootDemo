package com.example.oss.autoconfigure;

import com.aliyun.oss.OSSClient;
import com.example.oss.config.ProjectConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * oss 配置类
 *
 * @author lz
 * @date 2018/7/6
 */
@Configuration
@ConditionalOnClass({OSSClient.class})
@EnableConfigurationProperties(ProjectConfig.class)
public class OssAutoConfiguration {

    private final ProjectConfig projectConfig;

    public OssAutoConfiguration(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
    }

    @Bean
    public OssClientFactoryBean ossClientFactoryBean() {
        final OssClientFactoryBean factoryBean = new OssClientFactoryBean();
        factoryBean.setEndpoint(this.projectConfig.getOssEndpoint());
        factoryBean.setAccessKeyId(this.projectConfig.getOssAccessKeyId());
        factoryBean.setAccessKeySecret(this.projectConfig.getOssAccessKeySecret());
        return factoryBean;
    }
}
