package com.company;

import java.util.Scanner;

public class Solution2013 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[scanner.nextInt()];
        int min = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int n = scanner.nextInt();
            if (min > n) {
                min = n;
                count = 1;
            }
            else if (min == n)
                count++;
        }
        System.out.println(count);
    }
}