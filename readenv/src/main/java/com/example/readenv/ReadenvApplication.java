package com.example.readenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadenvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadenvApplication.class, args);
        String aaa = Global.getConfig("aaa",String.class,"ccc");
        System.out.println(aaa);
    }

}
