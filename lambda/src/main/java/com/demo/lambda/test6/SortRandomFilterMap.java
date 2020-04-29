package com.demo.lambda.test6;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * stream()：该方法返回一个将集合视为源的连续流。
 */
public class SortRandomFilterMap {
    public static void main(String[] args) {
        map();
    }

    /**
     * sorted：该方法用于对Stream排序。下面的代码段演示了怎样以有序的形式输出10个随机数。
     */
    private static void fun4() {
        Random random = new Random();
        random.ints(0,100).limit(10).sorted().forEach(System.out::println);
    }

    /**
     * filter：该方法用于过滤满足条件的元素。下面的代码段演示了怎样输出使用了过滤方法的空字符串数量。
     */
    private static void fun3() {
        List<String> strings = Arrays.asList("efg", "", "abc", "bc", "ghij", "", "lmn");
        //get count of empty string
        long count = strings.stream().filter(String::isEmpty).count();
        System.out.println(count);
    }

    /**
     * limit：该方法用于减少Stream的大小。下面的代码段演示了怎样有限制地输出10个随机数。
     * forEach：该方法用于对Stream中的每个元素进行迭代操作。下面的代码段演示了如何使用forEach方法输出10个随机数。
     */
    public static void fun() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    /**
     * map：该方法用于将每个元素映射到对应的结果上。下面的代码段演示了怎样用map方法输出唯一的某个数的平方。
     */
    public static void map() {
        List<Integer> numbers = Arrays.asList(2, 3, 3, 2, 5, 2, 7);
        //get list of unique squares
        // distinct 去重
        //collect()方法是在原数据集的基础上进行更新，过程中不产生新的数据集。
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);
    }


}
