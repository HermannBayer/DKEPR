package RegService;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/people")
class Controller {

  private final PersonRepository repository;

  Controller(PersonRepository repository) {
    this.repository = repository;
  }
  

  @GetMapping("/people") // localhost:8080/people?user=fish&pwd=1234
  @ResponseBody
  public List<Person> login(
    @RequestParam(required = false, name = "user") String u, 
    @RequestParam(required = false, name = "pwd") String p) {
	return repository.findByUserAndPwd(u,p);
  }

  @PostMapping("/people") // localhost:8080/people {  "firstName" : "Laurence",  "lastName" : "Fishburne", "user" : "fish", "pwd" : "123"}
  Person newPerson(@RequestBody Person newPerson) {
    return repository.save(newPerson);
  }

  @PutMapping("/people/{id}") // localhost:8080/people/168 {  "firstName" : "James", "lastName" : "Thomson", "user" : "fish", "pwd" : "1234"}
  Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(person -> {
//        person.setName(newPerson.getName());
        person.setFirstName(newPerson.getFirstName());
        person.setLastName(newPerson.getLastName());
        person.setUser(newPerson.getUser());
        person.setPwd(newPerson.getPwd());
        return repository.save(person);
      })
      .orElseGet(() -> {
        newPerson.setId(id);
        return repository.save(newPerson);
      });
  }

  @DeleteMapping("/people/{id}") // http://localhost:8080/people/170
  void deletePerson(@PathVariable Long id) {
    repository.deleteById(id);
  }

}