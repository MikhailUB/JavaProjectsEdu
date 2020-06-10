package com.company;

import java.util.Scanner;

public class Solution2038 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] words = str.split("[\\d\\W,\\s\\-:?._*$]");
        int max = 0;
        for (String word : words) {
            if (word.length() > max)
                max = word.length();
        }
        System.out.println(max);
    }
}
