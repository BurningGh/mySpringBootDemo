package com.demo.lambda.predicateFunction;

import java.util.function.Predicate;

/**
 * Predicate
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<Integer> p = age -> age > 18;
        boolean test = p.test(19);
        System.out.println(test);

        Predicate<Integer> p1 = age -> age < 30;
        Predicate<Integer> and = p1.and(p);
        //两个判断相加，与运算
        boolean test1 = and.test(29);
        System.out.println(test1);

    }
}
