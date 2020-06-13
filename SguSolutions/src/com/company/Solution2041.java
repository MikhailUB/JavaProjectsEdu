package com.company;

import java.util.Scanner;

public class Solution2041 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        for (int i = 0; i < str.length(); i++) {
            boolean ok1 = true;
            for (int j = i, k = str.length() - 1; j <= k; j++, k--) {
                if (str.charAt(j) != str.charAt(k)) {
                    ok1 = false;
                    break;
                }
            }
            if (!ok1)
                continue;

            boolean ok2 = true;
            for (int j = 0, k = i - 1; j <= k; j++, k--) {
                if (str.charAt(j) != str.charAt(k)) {
                    ok2 = false;
                    break;
                }
            }
            if (ok2)
            {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
}
