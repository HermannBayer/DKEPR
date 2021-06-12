package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User find(@PathVariable long id){
        //long lg = 123;
        //return new User(lg, "firstName", "lastName", "user", "pwd");
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/{id}/follows")
    public List<User> getFollows(@PathVariable long id){
        return  userRepository.getAllById(id);
        //return  userRepository.findById(id).orElse(null);
    }
/*
    @GetMapping("/{id}/followedBy")
    public Person getFollowedBy(@PathVariable long id){
        return  personRepository.findById(id).orElse(null);
    }
*/

    @GetMapping("/{username}/{pwd}")
    @ResponseBody
    public boolean existsByUserNameAndPwd(String username, String pwd) {
        return userRepository.existsByUserNameAndPwd(username, pwd);
    }


    @GetMapping("/login")
    @ResponseBody
    public List<User> login(
            @RequestParam(required = false, name = "userName") String name1,
            @RequestParam(required = false, name = "pwd") String name2) {
        return userRepository.findByUserNameAndPwd(name1,name2);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @PutMapping("/{id}/setFollows/{idFollows}")
    User setFollows(@PathVariable Long id, @PathVariable Long idFollows) {
    	System.out.println(id);

        User followsUser = userRepository.findById(idFollows).orElse(null);
        //some exception handling whatever

        return userRepository.findById(id)
                .map(user -> {
                    user.addFollows(followsUser);
                    return userRepository.save(user);
                }).orElse(null);

    }

    //Delete follow implementieren

    @PutMapping("/change/{id}")
    User replacePerson(@RequestBody User newPerson, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(person -> {
//        person.setName(newPerson.getName());
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    person.setUserName(newPerson.getUserName());
                    person.setPwd(newPerson.getPwd());
                    return userRepository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return userRepository.save(newPerson);
                });
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
