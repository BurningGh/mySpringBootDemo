package com.demo.lambda.test8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Nashorn JavaScript引擎
 *
 * @author lz
 * @date 2019/10/15
 */
public class NashornJavaScriptTest {
    public static void main(String[] args) {
        try {
            test();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static void test() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        System.out.println(engine.getClass().getName());
        System.out.println("Result:" + engine.eval("function f() { return 1; }; f() + 1;"));
    }
}
