package com.example.mybatisplus;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dengwenjian
 * @date 2020/12/21
 */
@Slf4j
@RestController
@RequestMapping("hello")
public class HelloController {

    @Resource
    private StudentProperty studentProperty;

    /** 必须在NacosPropertySource中配置了自动刷新为true，这里的autoRefreshed才能生效 */
    @NacosValue(value = "${name}",autoRefreshed = true)
    private String name;

    @GetMapping("name")
    public String name(){
        return "hello, "+name;
    }

    @GetMapping("student")
    public String student(){
        return studentProperty.toString();
    }

}
