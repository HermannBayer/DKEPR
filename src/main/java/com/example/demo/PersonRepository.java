package com.example.demo;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<User, Long> {
    User getPersonByFirstName(String name);
    List<User> findByUserAndPwd(@Param("user") String name1, @Param("pwd") String name2);
}
