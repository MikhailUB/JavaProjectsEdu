package com.company;

import java.util.Random;

public class TerminalServer {
    private int balance = new Random().nextInt(3000);

    public int getBalance() {
        return balance;
    }

    public void putToBalance(int value) {
        balance += value;
    }

    public void pullFromBalance(int value) {
        balance -= value;
    }
}
