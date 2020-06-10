package com.company;

import java.util.Scanner;

public class Solution2008 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int count = 0;
        int loaded = 0;
        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            if (loaded + m <= w)
            {
                loaded += m;
                count++;
                if (loaded == w)
                    break;
            }
        }
        System.out.println(count + " " + loaded);
    }
}
