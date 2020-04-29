package com.demo.lambda.test6;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * Java 8引入了用于统计的Collector来计算Stream处理完成后的所有统计数据。
 */
public class IntStatistic {
    public static void main(String[] args) {
        fun1();
    }

    /**
     * Java 8引入了用于统计的Collector来计算Stream处理完成后的所有统计数据。
     */
    private static void fun1() {
        List<Integer> numbers = Arrays.asList(2, 3, 3, 2, 5, 2, 7);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all numbers : " + stats.getAverage());
    }
}
