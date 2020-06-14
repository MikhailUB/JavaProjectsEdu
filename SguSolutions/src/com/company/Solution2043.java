package com.company;

import java.util.*;
import java.util.regex.*;

public class Solution2043 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        Pattern pattern = Pattern.compile("</*.>");
        Matcher matcher = pattern.matcher(text);
        int h = 0;
        while (matcher.find()) {
            String tag = text.substring(matcher.start(), matcher.end());
            if (tag.startsWith("</"))
                h--;
            System.out.printf("%" + (2 * h + tag.length()) + "s\n", tag);
            if (!tag.startsWith("</"))
                h++;
        }
    }
}
