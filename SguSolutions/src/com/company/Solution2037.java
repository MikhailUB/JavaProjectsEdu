package com.company;

import java.util.Scanner;

public class Solution2037 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int max = scanner.nextInt();
        String[] words = str.split("[,]", -1);
        boolean next = false;
        for (String word : words) {
            if (word.length() >= max) {
                if (next)
                    System.out.print(",");
                System.out.print(word);
                next = true;
            }
        }
    }
}
