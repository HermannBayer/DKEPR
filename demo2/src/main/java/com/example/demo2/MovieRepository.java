package com.example.demo2;

import java.util.List;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface MovieRepository extends ReactiveNeo4jRepository<MovieEntity, String> {
	List<MovieEntity> findOneByTitle(String title);
}