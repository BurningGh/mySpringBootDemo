package com.example.datarest.controller;

import com.example.datarest.model.Test;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author lz
 * @date 2018/12/26
 */
@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("list")
    public Test list() {
        Test test = new Test();
        test.setId(1);
        test.setAge(1);
        test.setBirth(new Date());
        test.setName("张三");
        return test;
    }

    @GetMapping("{id}/get")
    public Test get( @PathVariable("id") Integer id) {
        Test test = new Test();
        test.setId(id);
        test.setAge(1);
        test.setBirth(new Date());
        test.setName("张三");
        return test;
    }

    @PutMapping("{id}/put")
    public Test put(Test test, @PathVariable("id") Integer id) {
        test.setId(id);
        return test;
    }

    @PostMapping
    public Test post(Test test) {
        return test;
    }

}
