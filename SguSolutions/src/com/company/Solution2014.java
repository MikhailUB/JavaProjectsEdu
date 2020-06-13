package com.company;

import java.util.Scanner;

public class Solution2014 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int count = a > b ? 1 : 0;
        while (b != 0)
        {
            count++;
            b = a % (a = b);
        }
        System.out.println(count + " " + Math.abs(a));
    }

    public static void main1(String[] args) {
        int a = 0;
        int b = 0;
        if (a == 0) {
            System.out.println(0 + " " + b);
            return;
        }
        if (b == 0) {
            System.out.println(0 + " " + a);
            return;
        }
        int count;
        int res = 0;
        for (count = 1; ; count++) {
            if ((a %= b) == 0) {
                res = b;
                break;
            }
            else if ((b %= a) == 0) {
                res = a;
                break;
            }
        }
        System.out.println(++count + " " + res);
    }
}
