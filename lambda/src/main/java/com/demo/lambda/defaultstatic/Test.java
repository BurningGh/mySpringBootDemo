package com.demo.lambda.defaultstatic;

public class Test {
    public static void main(String[] args) {
        Ifunctional ifunctional = new IfunctionalImpl();
        ifunctional.defaultMethod();
        ifunctional.method();
        Ifunctional.staticMethod();
    }
}
