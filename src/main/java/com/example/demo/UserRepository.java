package com.example.demo;

import org.neo4j.driver.Session;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, Long> {
    User getUserByFirstName(String name);
    List<User> findByUserAndPwd(@Param("user") String name1, @Param("pwd") String name2);

    @Query("MATCH(User)-->(n) WHERE ID(User) = $idn RETURN n")
    List<User> getAllById(long idn);
}
