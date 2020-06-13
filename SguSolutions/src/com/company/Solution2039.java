package com.company;

import java.util.Scanner;

public class Solution2039 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] words = str.split("[\\d\\W,\\s\\-:?._*$]");
        int count = 0;
        for (String w : words) {
            if (w.length() > 0)
                count++;
        }
        System.out.println(count);
    }
}
