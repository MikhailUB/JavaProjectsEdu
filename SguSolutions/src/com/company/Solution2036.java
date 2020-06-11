package com.company;

import java.util.Scanner;

public class Solution2036 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = new String[Integer.parseInt(scanner.nextLine())];
        for (int i = 0; i < words.length; i++) {
            words[i] = scanner.nextLine();
        }
        for (String word : words) {
            if (!HasThree(word))
                System.out.println(word);
        }
    }

    private static boolean HasThree(String str)
    {
        int count = 0;
        for (int j = 0; j < str.length(); j++) {
            switch (str.charAt(j)) {
                case 'a':
                case 'e':
                case 'y':
                case 'u':
                case 'i':
                case 'o':
                    count++;
                    break;
                default:
                    count = 0;
                    break;
            }
            if (count >= 3)
                return true;
        }
        return false;
    }
}
