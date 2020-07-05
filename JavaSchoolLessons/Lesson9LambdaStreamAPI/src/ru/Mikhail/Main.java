package ru.Mikhail;

import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        List<Person> someCollection = new ArrayList<>();
        someCollection.add(new Person("Mikhail", 41));
        someCollection.add(new Person("Ekaterina", 17));
        someCollection.add(new Person("Andrey", 25));
        someCollection.add(new Person("Marina", 47));

        Map<String,Person> m = Streams
                .of(someCollection)
                .filter(p -> p.getAge() > 20)
                .transform(p -> new Person(p.getName(), p.getAge() + 30))
                .toMap(p -> p.getName(), p -> p);

        m.forEach((k, v) -> System.out.println("key: " + k + " value: " + v));

        someCollection.forEach(System.out::println);
    }
}
