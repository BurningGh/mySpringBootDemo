package com.example.mybatisplus;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * 务必要加Component注解，否则beanPostProcessor处理不到
 * @author dengwenjian
 * @date 2021/1/16
 */
@NacosConfigurationProperties(dataId = "student",type = ConfigType.PROPERTIES,autoRefreshed = true)
@Data
@Component
@ToString
public class StudentProperty {
    private String name;
    private String sex;
    private Integer age;
}
