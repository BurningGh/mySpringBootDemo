package com.demo.lambda.predicateFunction;

import java.util.function.Supplier;

/**
 * Supplier
 */
public class SupplierTest4 {
    public static void main(String[] args) {
       /* Supplier<String> supplier = () -> "这是返回的字符串";
        String s = supplier.get();

        System.out.println(s);*/

        Supplier<Student> supplier =Student::new;
        Student student = supplier.get();

        System.out.println(student);
    }

    static class Student {
        private String name;

        public Student() {
            this.name = "111111";
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
