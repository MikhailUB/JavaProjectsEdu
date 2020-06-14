package com.company;

import java.util.Scanner;

public class Solution2051 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '(')
                continue;

            int count = 1;
            for (int j = i + 1; j < str.length(); j++) {
                count += (str.charAt(j) == '(') ? 1 : -1;
                if (count == 0) {
                    System.out.println((i + 1) + " " + (j + 1));
                    //System.out.printf("%d %d\n", i + 1, j + 1);
                    break;
                }
            }
        }
    }
}