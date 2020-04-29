package com.demo.lambda.ifunctional;

public class Test {
    static String name = new String("abcd");

    public static void main(String[] args) {
        //传统方式
       /* Ifuncationle ifuncationle = new Ifuncationle<String, Integer>() {
            @Override
            public Integer handle(String s) {
                return Integer.parseInt(s);
            }
        };*/
        //第二种
       /* Ifuncationle<String,Integer> fun=s->{
            return Integer.parseInt(s);
        };*/


        //简化版
//         Ifuncationle<String,Integer> map=s->Integer.parseInt(s);


        //静态方法的引用
        //实现的方法只有一行代码的时候，并且该静态方法接受的参数类型和函数式接口的参数列表是一致的时候。
        // 以及静态方法的返回类型和函数式接口的的返回类型一致的时候。就可以使用类方法调用
        /*Ifuncationle<String, Integer> fun3 = Integer::parseInt;
        Integer handle = fun3.handle("123");
        System.out.println(handle);*/


        //对象方法的引用
        // 直接调用对象的方法，参数类型和返回值一致。
        //把对象的方法传递给函数式接口的方法实现
        /*Ifuncationle<String, Integer> fun4 = name::indexOf;
        Integer c = fun4.handle("c");
        System.out.println(c);*/

        //构造方法的引用
//        PersonFactory factory = ((firstName, lastName) -> new Person(firstName, lastName));

        PersonFactory personFactory = Person::new;
        Person person = personFactory.create("aa", "bb");

        System.out.println(person.toString());
    }

    static class Person {
        String firstName;
        String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Persion{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

    interface PersonFactory {
        Person create(String firstName, String lastName);
    }
}
