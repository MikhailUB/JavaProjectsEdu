package com.company;

import java.util.Scanner;

public class Solution2018 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[scanner.nextInt()];
        int j = 1;
        int count = j;
        boolean plus = true;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int value = scanner.nextInt();
            sum += (plus ? 1 : -1) * value;
            count--;
            if (count == 0) {
                j++;
                count = j;
                plus = !plus;
            }
        }
        System.out.println(sum);
    }
}
