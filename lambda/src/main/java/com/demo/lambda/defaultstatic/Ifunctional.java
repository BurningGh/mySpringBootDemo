package com.demo.lambda.defaultstatic;

public interface Ifunctional {
    void method();

    default void defaultMethod() {
        System.out.println("接口的默认方法");
    }

    static void staticMethod() {
        System.out.println("接口的静态方法");
    }
}
