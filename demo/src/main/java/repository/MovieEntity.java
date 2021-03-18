package repository;


import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;


@Node("Movie")
public class MovieEntity {
	@Id
	private final String title;
	@Property("tagline")
	private final String description;
	@Relationship(type = "ACTED_IN", direction = Direction.INCOMING)
	private Set<PersonEntity> actors = new HashSet<>();
	@Relationship(type = "DIRECTED", direction = Direction.INCOMING)
	private Set<PersonEntity> directors = new HashSet<>();
	public MovieEntity(String title, String description) {
		this.title = title;
		this.description = description;
	}
	//Getters omitted for brevity
}
