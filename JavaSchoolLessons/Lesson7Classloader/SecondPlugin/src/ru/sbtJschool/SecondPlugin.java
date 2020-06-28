package ru.sbtJschool;

import ru.Mikhail.Plugin;

public class SecondPlugin implements Plugin {
    private String name = "SecondPlugin 1.0.2";

    @Override
    public void doUsefull() {
        System.out.println(name + " you in doUsefull() method");
    }

    @Override
    public void printName() {
        System.out.println(name);
    }
}
