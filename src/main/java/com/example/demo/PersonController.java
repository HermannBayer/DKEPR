package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;
    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @GetMapping
    public Iterable<Person> findAll(){
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person find(@PathVariable long id){
        return personRepository.findById(id).orElse(null);
    }

    @GetMapping("/{id}/follows")
    public Person getFollows(@PathVariable long id){
        return  personRepository.findById(id).orElse(null);
    }
/*
    @GetMapping("/{id}/followedBy")
    public Person getFollowedBy(@PathVariable long id){
        return  personRepository.findById(id).orElse(null);
    }
*/
    @GetMapping("/login")
    @ResponseBody
    public List<Person> login(
            @RequestParam(required = false, name = "user") String name1,
            @RequestParam(required = false, name = "pwd") String name2) {
        return personRepository.findByUserAndPwd(name1,name2);
    }

    @PostMapping("/addPerson")
    public Person addPerson(@RequestBody Person newPerson) {
        System.out.println("hallo");
        return personRepository.save(newPerson);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllPersons() {
        personRepository.deleteAll();
    }

    @PutMapping("/{id}/setFollows/{idFollows}")
    Person setFollows(@PathVariable Long id, @PathVariable Long idFollows) {
    	System.out.println(id);

        Person followsPerson = personRepository.findById(idFollows).orElse(null);
        //some exception handling whatever

        return personRepository.findById(id)
                .map(person -> {
                    person.addFollows(followsPerson);
                    return personRepository.save(person);
                }).orElse(null);

    }

/*    @PutMapping("/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {

        return personRepository.findById(id)
                .map(person -> {
//        person.setName(newPerson.getName());
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    person.setUser(newPerson.getUser());
                    person.setPwd(newPerson.getPwd());
                    return personRepository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);
                });
    }*/

    @DeleteMapping("/{id}")
    void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
}
