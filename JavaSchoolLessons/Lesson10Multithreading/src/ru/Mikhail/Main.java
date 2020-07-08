package ru.Mikhail;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        for (String line : lines) {
            int n = Integer.parseInt(line);
            FactorialCalculator calculator = new FactorialCalculator(n);
            Thread thread = new Thread(calculator);
            thread.start();
        }
        Thread.sleep(1000);
    }
}
