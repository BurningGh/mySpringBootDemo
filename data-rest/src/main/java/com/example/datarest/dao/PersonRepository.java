package com.example.datarest.dao;

import com.example.datarest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * person
 *
 * @author lz
 * @date 2019/12/31
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @RestResource(path = "nameStartsWith", rel = "nameStartsWith")
    List<Person> findByNameStartingWith(@Param("name") String name);
}
