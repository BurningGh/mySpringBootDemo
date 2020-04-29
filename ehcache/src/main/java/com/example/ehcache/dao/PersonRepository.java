package com.example.ehcache.dao;

import com.example.ehcache.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lz
 * @date 2018/12/27
 *
 */
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
