package com.company;

import java.util.Scanner;

public class Solution2007 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int count = scanner.nextInt();
        int res = 0;
        while (count % 2 == 0) {
            res++;
            count /= 2;
        }
        System.out.println(res);
    }
}

