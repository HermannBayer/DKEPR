package repository;

import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

import domain.MovieEntity;

public interface MovieRepository extends ReactiveNeo4jRepository<MovieEntity, String> {
	Mono<MovieEntity> findOneByTitle(String title);
}
