package com.demo.lambda.test9;

/**
 * @author lz
 * @date 2019/10/21
 */
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}