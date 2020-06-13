package com.company;

import java.io.*;
import java.util.*;

public class Solution2057 {
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("input.txt");
        Scanner scanner = new Scanner(file);
        int count = scanner.nextInt();

        SortedMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < count; i++) {
            boolean add = scanner.nextInt() == 1;
            if (add) {
                Integer key = scanner.nextInt();
                Integer value = map.getOrDefault(key, 0);
                map.put(key, value + 1);
            }
            else {
                Integer minKey = map.firstKey();
                Integer value = map.get(minKey);
                if (value > 1)
                    map.replace(minKey, value - 1);
                else
                    map.remove(minKey);

                System.out.println(minKey);
            }
        }
        scanner.close();
        file.close();
    }
}
