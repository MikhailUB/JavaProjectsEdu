package com.company;

import java.util.*;

/*
3. Параметризовать CountMap и реализовать его в CountMapIml.
4. Параметризовать методы CollectionUtils, используя правило PECS, и реализовать их
*/
public class Main {

    public static void main(String[] args) {
        // пример использования CountMap
        CountMap<Integer> map = new CountMapIml<>();

        ArrayList<Integer> integers = new ArrayList<>();
        integers.toArray();
        map.add(10);
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(5);
        map.add(6);

        System.out.println(map.getCount(6)); // 1
        System.out.println(map.getCount(5)); // 2
        System.out.println(map.getCount(10)); // 3

        CountMap<Integer> map1 = new CountMapIml<>();
        map1.add(10);
        map1.add(1);
        map.addAll(map1);

        map.toMap().forEach((k, v) -> System.out.println(k + " " + v));

        Map<Number, Integer> dest = new HashMap<>();
        map1.toMap(dest);
        dest.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
