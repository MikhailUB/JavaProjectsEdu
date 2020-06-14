package com.company;

import java.util.Scanner;

public class Solution2020 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[scanner.nextInt()];
        int min = 0;
        int minCount = 0;
        int lastValue = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int value = scanner.nextInt();
            if (lastValue != value)
            {
                if (count > 0 && count > minCount) {
                    minCount = count;
                    min = lastValue;
                }
                count = 0;
            }
            count++;
            lastValue = value;
        }
        if (count > 0 && count > minCount) {
            minCount = count;
            min = lastValue;
        }
        System.out.printf("%d %d", min, minCount);
    }
}
