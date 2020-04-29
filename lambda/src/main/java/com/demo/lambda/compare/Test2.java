package com.demo.lambda.compare;

import java.util.Arrays;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "c", "b", "z", "f", "e");
/*        Collections.sort(list,(String o1,String o2)->{
            return Collator.getInstance().compare(o1,o2);
        });*/


        // Collections.sort(list,(String o1,String o2)-> Collator.getInstance().compare(o1,o2));
//        list.sort((o1, o2) -> Collator.getInstance().compare(o1, o2));
        list.sort(String::compareTo);


        System.out.println(list);
    }
}
