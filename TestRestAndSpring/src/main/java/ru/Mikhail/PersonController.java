package ru.Mikhail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PersonController {
    private Repository repository;

    @Autowired
    public PersonController(Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/person")
    public Person person(@RequestParam(value = "id") String id) {
        return repository.personById(id);
    }

    @RequestMapping(method = { RequestMethod.GET }, path = { "/persons" })
    //@GetMapping("/person")
    public Collection<Person> persons() {
        return repository.persons();
    }

    @PostMapping("/person")
    public Person personUpdate(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "age") int age) {

        Person person = id != null && !id.isEmpty() ? repository.personById(id) : null;
        if (person != null) {
            person.setName(name);
            person.setPhone(phone);
            person.setAge(age);
        }
        else {
            person = new Person(name, phone, age);
        }
        repository.addPerson(person);

        return person;
    }
}
