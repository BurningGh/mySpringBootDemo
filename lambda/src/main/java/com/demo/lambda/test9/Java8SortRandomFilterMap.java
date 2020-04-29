package com.demo.lambda.test9;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * @author lz
 * @date 2019/10/21
 */
public class Java8SortRandomFilterMap {
    @Test
    public void test1() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));

    }

    @Test
    public void test2() {

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

//        Collections.sort(names, (String a, String b) -> {
//            return b.compareTo(a);
//        });
//
//        Collections.sort(names, (String a, String b) -> b.compareTo(a));
//
//        Collections.sort(names, (a, b) -> b.compareTo(a));
//
//        Collections.sort(names, Comparator.reverseOrder());
//
//        names.sort(Comparator.reverseOrder());


        names.forEach(System.out::println);
    }

    @Test
    public void test3() {
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123
    }

    @Test
    public void test4() {
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person);
    }

    @Test
    public void test5() {
        final int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);

        String convert = stringConverter.convert(2);// 3
        System.out.println("convert = " + convert);
    }

    @Test
    public void test6() {
        Formula formula = (a) -> sqrt( a * 100);
    }


}

/**
 * 和本地变量不同的是，lambda内部对于实例的字段以及静态变量是即可读又可写。该行为和匿名对象是一致的
 */
class Lambda4 {
    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }
}

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}

class Person {
    String firstName;
    String lastName;

    Person() {
    }

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
