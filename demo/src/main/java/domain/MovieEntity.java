package domain;







import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Movie")
public class MovieEntity {
	@Id
	private final String title;
	@Property("tagline")
	private final String description;
	@Relationship(type = "ACTED_IN", direction = INCOMING)
	private Set<PersonEntity> actors = new HashSet<>();
	@Relationship(type = "DIRECTED", direction = INCOMING)
	private Set<PersonEntity> directors = new HashSet<>();
	public MovieEntity(String title, String description) {
		this.title = title;
		this.description = description;
	}
	//Getters omitted for brevity
}
