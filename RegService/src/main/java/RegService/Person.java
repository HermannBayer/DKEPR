package RegService;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.stereotype.Indexed;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Node
public class Person {
  @Id @GeneratedValue private Long id;

//  private String name;
  private String firstName;
  private String lastName;
  private String user;
  private String pwd;

//  @Relationship(type = "FOLLOWS", direction = Direction.INCOMING)
//  private List<Person> followedBy;
  
  @Relationship(type = "FOLLOWS", direction = Direction.OUTGOING)
  private List<Person> follows;
  
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
    
  }

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Long getId() {
		return id;
	}	

	public void setId(Long id) {
		this.id = id;
	}
/*
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getFollowedBy() {
		return followedBy;
	}

	public void setFollowedBy(List<Person> followedBy) {
		this.followedBy = followedBy;
	}
*/
	public List<Person> getFollows() {
		return follows;
	}

	public void setFollows(List<Person> follows) {
		this.follows = follows;
	}




  
  
}
