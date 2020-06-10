package com.company;

import java.util.Scanner;

public class Solution2021 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[scanner.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        for (int j = 0; j < 2; j++) {
            int max = Integer.MIN_VALUE;
            for (int value : arr) {
                if (max < value)
                    max = value;
            }
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == max)
                    arr[i] /= 2;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int value : arr) {
            res.append(value).append(" ");
        }
        System.out.println(res.toString().trim());
    }
}
