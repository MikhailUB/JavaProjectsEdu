package com.company;

import java.util.Scanner;

public class Solution2042 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String temp = scanner.nextLine();

        for (int i = 0; i < str.length() - temp.length() + 1; i++) {
            boolean ok = true;
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) != '?' && str.charAt(i + j) != temp.charAt(j)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.print((i + 1) + " ");
            }
        }
    }
}
