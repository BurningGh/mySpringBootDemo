package com.demo.lambda.test6;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Collector用于合并Stream的元素处理结果。它可以用于返回一个字符串列表。
 */
public class CollectorJoinSummingIntTest3 {
    public static void main(String[] args) {
        fun1();
    }

    /**
     * Collector用于合并Stream的元素处理结果。它可以用于返回一个字符串列表。
     */
    private static void fun1() {
        List<String> strings = Arrays.asList("efg", "", "abc", "bc", "ghij", "", "lmn");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("Filtered List: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Merged String: " + mergedString);
    }
}
