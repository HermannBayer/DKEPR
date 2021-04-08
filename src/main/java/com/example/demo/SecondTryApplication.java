package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SecondTryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondTryApplication.class, args);
	}

	@Bean
	public CommandLineRunner testDB(PersonRepository userRepository, PersonController userService) {
		return args -> {
			Person user = new Person();
			user.setFirstName("fistname");
			user.setLastName("lastname");
			user.setUser("username");
			user.setPwd("password");
			userRepository.save(user);

			// retrieve all
			Iterable<Person> users = userRepository.findAll();

			/*List<Person> u = userService.find("firstname");
			log.info("UseradminApplication, {}", u);*/
			// print all
			users.forEach(System.out::println);
		};
	}

}
