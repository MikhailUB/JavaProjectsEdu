package com.company;

public interface Calculator {
    @Cache
    int Plus(int value);

    @Cache
    int Minus(int value);
}
