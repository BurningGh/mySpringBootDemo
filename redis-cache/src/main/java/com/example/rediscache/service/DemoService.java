package com.example.rediscache.service;

import com.example.rediscache.entity.Person;

/**
 * @author lz
 * @date 2018/12/27
 *
 */
public interface DemoService {
    Person save(Person person);

    void remove(Integer id);

    Person findOne(Person person);
}
