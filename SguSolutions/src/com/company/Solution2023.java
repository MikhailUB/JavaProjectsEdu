package com.company;

import java.util.Scanner;

public class Solution2023 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[scanner.nextInt()];
        for (int i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[scanner.nextInt()];
        for (int j = 0; j < b.length; j++) {
            b[j] = scanner.nextInt();
        }
        int count = 0;
        int[] m = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int value : b) {
                if (a[i] == value) {
                    m[i] = a[i];
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
        if (count > 0) {
            for (int value : m) {
                if (value > 0)
                    System.out.print(value + " ");
            }
        }
    }
}