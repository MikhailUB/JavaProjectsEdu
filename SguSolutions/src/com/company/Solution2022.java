package com.company;

import java.util.Scanner;

public class Solution2022 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[scanner.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j)
                    continue;
                if (arr[i] % arr[j] == 0)
                    count++;
            }
        }
        System.out.println(count);
    }
}
