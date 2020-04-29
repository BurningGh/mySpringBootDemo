package com.demo.lambda.ifunctional;

/**
 * 函数式接口不允许超过一个以上的函数声明<br/>
 * 但是可以有 default 方法和 static 方法
 * @param <T>
 * @param <R>
 */
@FunctionalInterface//函数式接口的声明注解
public interface Ifuncationle<T,R> {
    R handle(T t);
}
