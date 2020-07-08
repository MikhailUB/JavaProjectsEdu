package ru.Mikhail;

import java.math.BigInteger;

public class FactorialCalculator implements Runnable {
    private BigInteger result;
    private int n;

    public FactorialCalculator(int n) {
        this.n = n;
    }

    public BigInteger getResult() {
        return result;
    }

    public int getN() {
        return n;
    }

    @Override
    public void run() {
        result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "n= " + n + " result= " + result;
    }
}
