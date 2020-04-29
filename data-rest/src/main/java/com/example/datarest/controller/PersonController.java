package com.example.datarest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @RequestMapping("/hello")
    public String hello() {
        return " Hello,welcome to the normal controller! ";
    }

}