package ru.Mikhail;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Repository {
    public static Map<String, Person> persons = new Hashtable<>();

    static {
        Person[] personsArr = {
                new Person("Сергей", "123-45-67", 25),
                new Person("Андрей", "234-56-78", 19),
                new Person("Наталья", "345-67-89", 32),
                new Person("Александр", "456-78-90", 28),
                new Person("Кристина", "567-89-01", 34),
        };
        for (Person person : personsArr) {
            persons.put(person.getId(), person);
        }
    }

    public Person personById(String id) {
        return persons.get(id);
    }

    public Collection<Person> persons() {
        return persons.values();
    }

    public Person addPerson(Person person) {
        persons.put(person.getId(), person);
        return person;
    }
}
