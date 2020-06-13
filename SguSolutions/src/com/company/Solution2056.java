package com.company;

import java.io.*;
import java.util.*;

public class Solution2056 {
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("input.txt");
        BufferedReader reader = new BufferedReader(file);
        HashMap<String, Integer> map = new HashMap<>();
        int max = 0;
        String str;
        while ((str = reader.readLine()) != null) {
            String[] words = str.split("\\s");
            for (String word : words) {
                if (word == null || word.isEmpty())
                    continue;
                String w = word.toLowerCase();
                Integer count = map.getOrDefault(w, 0);
                count++;
                map.put(w, count);
                max = max < count ? count : max;
            }
        }
        reader.close();
        file.close();

        ArrayList<Map.Entry<String,Integer>> list = new ArrayList(map.entrySet());
        list.sort((o1, o2) -> o1.getKey().compareTo(o2.getKey()));
        FileWriter writer = new FileWriter("output.txt", false);
        for (Map.Entry<String, Integer> pair : list) {
            if (pair.getValue() == max)
                writer.write(pair.getKey() + "\n");
        }
        writer.flush();
        writer.close();
    }
}
