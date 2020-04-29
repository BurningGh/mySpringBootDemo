package com.demo.lambda.compare;

import java.text.Collator;
import java.util.Arrays;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "c", "b", "z", "f", "e");
        //传统方式
        Collections.sort(list, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return Collator.getInstance().compare(o1, o2);
                    }
                }
        );
        System.out.println(list);





    }
}
