package ru.Mikhail;

import java.util.UUID;

public class Person {
    private final String id;
    private String name;
    private String phone;
    private int age;

    public Person(String name, String phone, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
