package com.demo.lambda.test8;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Optional
 *
 * @author lz
 * @date 2019/10/14
 */
public class OptionalFilesTest {
    public static void main(String[] args) {
        test3("/Users/crc/Documents/temp/test.text");
    }

    /**
     * Java应用中最常见的bug就是空值异常。在Java 8之前，Google Guava引入了Optionals类来解决NullPointerException，从而避免源码被各种null检查污染，以便开发者写出更加整洁的代码。Java 8也将Optional加入了官方库。
     * <p>
     * Optional仅仅是一个容易：存放T类型的值或者null。它提供了一些有用的接口来避免显式的null检查，可以参考Java 8官方文档了解更多细节。
     * <p>
     * 接下来看一点使用Optional的例子：可能为空的值或者某个类型的值：
     */
    public static void test1() {
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is set? " + fullName.isPresent());
        System.out.println("Full Name: " + fullName.orElse("[none]"));
        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    }

    public static void test2() {
        Optional<String> firstName = Optional.of("Tom");
        System.out.println("First Name is set? " + firstName.isPresent());
        System.out.println("First Name: " + firstName.orElse("[none]"));
        System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
        System.out.println();
    }

    public static void test3(String filename) {
        final Path path = new File(filename).toPath();
//        try
//
//                (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8))
//        {
//            lines.onClose(() -> System.out.println("Done!")).forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            Files.lines(path, StandardCharsets.UTF_8).onClose(()->System.out.println("Done!")).forEach(System.out::println);

        }catch (Exception e){

        }
    }


}
