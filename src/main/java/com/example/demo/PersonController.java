package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;
    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @GetMapping
    public Iterable<User> findAll(){
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public User find(@PathVariable long id){
        return personRepository.findById(id).orElse(null);
    }

    @GetMapping("/{id}/follows")
    public User getFollows(@PathVariable long id){
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
    public List<User> login(
            @RequestParam(required = false, name = "user") String name1,
            @RequestParam(required = false, name = "pwd") String name2) {
        return personRepository.findByUserAndPwd(name1,name2);
    }

    @PostMapping("/addPerson")
    public User addPerson(@RequestBody User newUser) {
        System.out.println("hallo");
        return personRepository.save(newUser);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllPersons() {
        personRepository.deleteAll();
    }

    @PutMapping("/{id}/setFollows/{idFollows}")
    User setFollows(@PathVariable Long id, @PathVariable Long idFollows) {
    	System.out.println(id);

        User followsUser = personRepository.findById(idFollows).orElse(null);
        //some exception handling whatever

        return personRepository.findById(id)
                .map(user -> {
                    user.addFollows(followsUser);
                    return personRepository.save(user);
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
