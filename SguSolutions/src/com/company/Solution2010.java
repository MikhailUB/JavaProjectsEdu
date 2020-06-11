package com.company;

import java.util.Scanner;

public class Solution2010 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int count = 0;
        while (n > 0 && m > 0)
        {
            if (n > m)
                n -= m;
            else
                m -= n;
            count++;
        }
        System.out.println(count + " " + (n > m ? n : m));
    }
}
