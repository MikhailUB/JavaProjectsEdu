package com.company;

public class Main {

    public static void main(String[] args) {
        Person m1 = new Person(true, "Man 1");
        Person m2 = new Person(true, "Man 2");
        Person w1 = new Person(false, "Woman 1");
        Person w2 = new Person(false, "Woman 2");

        m1.marry(m2);
        System.out.println(m1);
        m1.marry(w1);
        System.out.println(m1);

        w2.marry(w1);
        System.out.println(w2);
        w2.marry(m2);
        System.out.println(w2);

        m1.marry(w2);
        System.out.println(m1);
        System.out.println(w2);
        System.out.println(m2);
        System.out.println(w1);
    }
}
