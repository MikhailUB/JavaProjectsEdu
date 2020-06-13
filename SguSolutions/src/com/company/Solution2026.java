package com.company;

import java.util.*;

public class Solution2026 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[scanner.nextInt()];
        for (int i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            boolean hasMax = false;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > a[i])
                {
                    b[i] = a[j];
                    hasMax = true;
                    break;
                }
            }
            if (!hasMax)
                b[i] = 0;
        }
        for (int value : b) {
            System.out.print(value + " ");
        }
    }
}
