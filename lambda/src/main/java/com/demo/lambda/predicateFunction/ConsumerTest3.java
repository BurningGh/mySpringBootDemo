package com.demo.lambda.predicateFunction;

import java.util.function.Consumer;

/**
 * Consumer
 */
public class ConsumerTest3 {
    public static void main(String[] args) {
        Consumer<String> consumer=System.out::println;
        consumer.accept("abc");
    }
}
