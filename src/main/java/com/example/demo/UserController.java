package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    //neu
   @GetMapping("/username/{username}")
    User getUsersByUsername(@PathVariable String username){
        User singelUser = userRepository.findByUserName(username);
        return singelUser;
    }

    //neu
    @GetMapping("/id/{id}")
    public List<User> getUserById(@PathVariable long id){
        return userRepository.findUsersByIdContaining(id);
    }

    @GetMapping("/{id}/follows")
    public List<User> getFollows(@PathVariable long id){
        return  userRepository.getAllById(id);
    }

    public List<User> getFollowing(@PathVariable long id){
        return  userRepository.getAllById(id);
    }

    @GetMapping("/{id}/followedBy")
    public List<User> getFollowedBy(@PathVariable long id){
        return userRepository.getUserBy(id);
    }

    public List<User> getFollowers(@PathVariable long id){
        return userRepository.getUserBy(id);
    }


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
    public User createUser(@RequestBody User newUser) {
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

        return userRepository.findById(id)
                .map(user -> {
                    user.addFollows(followsUser);
                    return userRepository.save(user);
                }).orElse(null);

    }


    public void addFollower(User userToFollow, User follower) {
        Optional<User> u = userRepository.findById(userToFollow.getId());
        if (u.isEmpty()) return;
        Optional<User> uFollower = userRepository.findById(follower.getId());
        if (uFollower.isEmpty()) return;
        if (getFollowedBy(userToFollow.getId()) == null) {
            List<User> newFollowList = new ArrayList<>();
            newFollowList.add(follower);
            userToFollow.setFollows(newFollowList);
        } else {
            List<User> follows = getFollowedBy(userToFollow.getId());
            follows.add(follower);
        }
        userRepository.save(userToFollow);
    }


    //Delete follow implementieren

    @PutMapping("/change/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User newPerson) {

        return userRepository.findById(id)
                .map(person -> {
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
