package com.jack.springboot8cache.service;

import com.jack.springboot8cache.entity.Person;

/**
 * @author lz
 * @date 2018/12/27
 */
public interface DemoService {
    Person save(Person person);

    void remove(Integer id);

    Person findOne(Person person);
}
