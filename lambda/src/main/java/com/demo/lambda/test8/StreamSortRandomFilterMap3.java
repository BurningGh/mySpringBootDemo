package com.demo.lambda.test8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author lz
 * @date 2019/10/21
 */
public class StreamSortRandomFilterMap3 {
    /**
     * 消费型接口Consumer<T>
     */
    @Test
    public void test1() {
        consumo(500, (x) -> System.out.println(x));
    }

    private void consumo(double money, Consumer<Double> c) {
        c.accept(money);
    }


    /**
     * 供给型接口，Supplier<T>
     */
    @Test
    public void test2() {
        Random ran = new Random();
        List<Integer> list = supplier(10, () -> ran.nextInt(10));

        for (Integer i : list) {
            System.out.println(i);
        }
    }

    /**
     * 随机产生sum个数量得集合
     *
     * @param sum 集合内元素个数
     * @param sup
     * @return
     */
    private List<Integer> supplier(int sum, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < sum; i++) {
            list.add(sup.get());
        }
        return list;
    }


    /**
     * 函数型接口：Function<R, T>
     */
    @Test
    public void test3() {
        String s = strOperar(" asdf ", x -> x.substring(0, 2));
        System.out.println(s);
        String s1 = strOperar(" asdfw ", String::trim);
        System.out.println(s1);
    }

    /**
     * 字符串操作
     *
     * @param str 需要处理得字符串
     * @param fun Function接口
     * @return 处理之后得字符传
     */
    private String strOperar(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    /**
     * 断言型接口：Predicate<T>
     */
    @Test
    public void test4() {
        List<Integer> l = new ArrayList<>();
        l.add(102);
        l.add(172);
        l.add(13);
        l.add(82);
        l.add(109);
        List<Integer> list = filterInt(l, x -> (x > 100));
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    /**
     * 过滤集合
     *
     * @param list
     * @param pre
     * @return
     */
    public List<Integer> filterInt(List<Integer> list, Predicate<Integer> pre) {
        List<Integer> l = new ArrayList<>();
        for (Integer integer : list) {
            if (pre.test(integer))
                l.add(integer);
        }
        return l;
    }
}
