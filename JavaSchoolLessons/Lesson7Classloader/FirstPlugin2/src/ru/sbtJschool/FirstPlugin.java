package ru.sbtJschool;

import ru.Mikhail.Plugin;

public class FirstPlugin implements Plugin {
    private String name = "FirstPlugin 222 1.1.1";

    @Override
    public void doUsefull() {
        System.out.println(name + " you in doUsefull() method");
    }

    @Override
    public void printName() {
        System.out.println(name);
    }
}