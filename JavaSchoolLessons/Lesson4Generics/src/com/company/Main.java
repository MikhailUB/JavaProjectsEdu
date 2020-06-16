package com.company;

import java.util.*;

/*
3. Параметризовать CountMap и реализовать его в CountMapIml.
4. Параметризовать методы CollectionUtils, используя правило PECS, и реализовать их
*/
public class Main {

    public static void main(String[] args) {
        // пример использования CountMap
        CountMap<Number> map = new CountMapIml<>();

        map.add(10);
        map.add(10);
        map.add(10.5);
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

        Map<Integer, Integer> dest = new HashMap<>();
        map.toMap().forEach((k, v) -> System.out.println(k + " " + v));

        map1.toMap(dest);
        dest.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
