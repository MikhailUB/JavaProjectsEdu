package com.company;

import java.util.Scanner;

public class Solution2011 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int y = scanner.nextInt();
        String view = "specialist";
        if (y < 7)
            view = "preschool child";
        else if (y <= 17)
            view = "schoolchild " + (y - 6);
        else if (y <= 22)
            view = "student " + (y - 17);
        System.out.println(view);
    }
}
