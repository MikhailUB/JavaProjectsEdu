package com.company;

import java.util.Scanner;

public class Solution2009 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int sum = 0;
        for (int i = 1; i <= count; i++) {
            int n = scanner.nextInt();
            if (i == 1 || (i & (i - 1)) == 0)
                sum += n;
        }
        System.out.println(sum);
    }
}