package com.company;

public class CalculatorImpl implements Calculator {
    @Override
    public int Plus(int value) {
        return 100 + value;
    }

    @Override
    public int Minus(int value) {
        return 100 - value;
    }
}
