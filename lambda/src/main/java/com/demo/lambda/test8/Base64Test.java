package com.demo.lambda.test8;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author lz
 * @date 2019/10/15
 */
public class Base64Test {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        final String text = "Base64 finally in Java 8!";

        final String encoded = Base64
                .getEncoder()
                .encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);

        final String decoded = new String(
                Base64.getDecoder().decode(encoded),
                StandardCharsets.UTF_8);
        System.out.println(decoded);

    }
}
