package com.example.demo;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node
public class User {
    @Id
    @GeneratedValue
    private Long id;

    //  private String name;
    private String firstName;
    private String lastName;
    private String user;
    private String pwd;

    public User() {

    }

    public User(Long id, String firstName, String lastName, String user, String pwd) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.pwd = pwd;
    }

    @Relationship(type = "FOLLOWS", direction = OUTGOING)
    private Set<User> follows= new HashSet<>();
/*
    @Relationship(type = "FOLLOWS", direction = INCOMING)
    private Set<Person> followedBy= new HashSet<>();
*/
    public void addFollows(User user){
        follows.add(user);
    }

    public Set<User> getFollows(){
        return follows;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
/*
	public Set<Person> getFollowedBy() {
		return followedBy;
	}

	public void setFollowedBy(Set<Person> followedBy) {
		this.followedBy = followedBy;
	}
*/
	public void setFollows(Set<User> follows) {
		this.follows = follows;
	}
}
