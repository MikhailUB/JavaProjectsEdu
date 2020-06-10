package com.company;

import java.util.Scanner;

public class Solution2024 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[scanner.nextInt()];
        for (int i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
        }
        int count = 0;
        for (int i = 0, j = a.length - 1; i <= j; i++, j--) {
            if (a[i] != a[j])
                count++;
        }
        System.out.println(count);
    }
}
