package com.demo.lambda.test6;

import java.util.Arrays;
import java.util.List;

/**
 * parallelStream()：该方法返回一个将集合视为源的并行流。
 */
public class ParallelStream {
    public static void main(String[] args) {
        fun1();
    }

    /**
     * ParallelStream是Stream用于并行处理的一种替代方案。下面的代码段演示了如何使用它来输出空字符串的数量。
     */
    private static void fun1() {
        List<String> strings = Arrays.asList("efg", "", "abc", "bc", "ghij", "", "lmn");
        // 获得空字符串的计数
        long count = strings.parallelStream().filter(String::isEmpty).count();
        System.out.println(count);
    }
}
