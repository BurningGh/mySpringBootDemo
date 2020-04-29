package com.demo.lambda.test8;

import org.junit.Test;

import java.beans.Encoder;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lz
 * @date 2019/10/21
 */
public class StreamSortRandomFilterMap2 {
    @Test
    public void test1() {
        Consumer<String> con = (x) -> System.out.println(x);

        //下面这种写法与上面写法效果一致
        PrintStream pr = System.out;
        Consumer<String> con1 = pr::println;
    }

    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        int b = com.compare(5, 6);

        Comparator<Integer> com1 = Integer::compare;
        int c = com1.compare(7, 8);
    }

    /**
     * 　如果是静态方法的话，首先也是要返回值和参数与Comparator中的compare方法得一致，
     * 然后可以直接使用类名 :: 方法名去引用这个方法。上面得接口中得实现已经由Integer中得compare方法实现了，
     * 而且两个方法得返回值与参数都一致，所以，可以这样 使用。
     */
    @Test
    public void test3() {
        BiPredicate<String, String> bi = (x, y) -> x.equals(y);

        BiPredicate<String, String> bi1 = String::equals;
    }

    @Test
    public void test4() {
        Supplier<Encoder> su = () -> new Encoder();

        Supplier<Encoder> su1 = Encoder::new;
    }

    @Test
    public void test5() {
        Function<Integer, Integer> fun = (x) -> new Integer(x);

        Function<Integer, Integer> fun1 = Integer::new;
    }

    @Test
    public void test6() {
        Function<Integer, String[]> fun = (x) -> new String[x];

        Function<Integer, String[]> fun1 = String[]::new;
    }
}
