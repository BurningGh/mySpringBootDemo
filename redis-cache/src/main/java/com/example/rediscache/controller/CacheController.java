package com.example.rediscache.controller;

import com.example.rediscache.entity.Person;
import com.example.rediscache.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lz
 * @date 2018/12/27
 *
 */
@RestController
@RequestMapping("cache")
public class CacheController {
    private final DemoService demoService;

    public CacheController(DemoService demoService) {
        this.demoService = demoService;
    }

    @RequestMapping("/put")
    public Person put(Person person) {
        return demoService.save(person);
    }

    @RequestMapping("/evict")
    public String evict(Integer id) {
        demoService.remove(id);
        return "ok";
    }

    @RequestMapping("/cacheable")
    public Person cacheable(Person person) {
        return demoService.findOne(person);
    }

}
