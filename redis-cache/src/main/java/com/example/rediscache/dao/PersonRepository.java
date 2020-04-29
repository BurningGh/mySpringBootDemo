package com.example.rediscache.dao;

import com.example.rediscache.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lz
 * @date 2018/12/27
 *
 */
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
