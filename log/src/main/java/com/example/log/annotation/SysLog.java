package com.example.log.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)//注解最终会在哪里使用，这里是定义注解会在方法上
@Documented//生成文档
@Retention(RetentionPolicy.RUNTIME)//运行时注解
public @interface SysLog {
    String title() default "";
    String action() default "";
}
