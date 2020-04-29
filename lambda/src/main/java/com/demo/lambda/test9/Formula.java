package com.demo.lambda.test9;

/**
 * @author lz
 * @date 2019/10/21
 */
@FunctionalInterface
public interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
