package com.company;

import java.util.*;

/*
3. Параметризовать CountMap и реализовать его в CountMapIml.
4. Параметризовать методы CollectionUtils, используя правило PECS, и реализовать их
*/
public class Main {

    public static void main(String[] args) {

        // пример использования CollectionUtil
        testCollectionUtil();

        // пример использования CountMap
        //testCountMap();
    }

    private static void testCountMap() {
        CountMap<Integer> map = new CountMapIml<>();

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

    private static void testCollectionUtil() {
        List<Integer> list = CollectionUtils.newArrayList();
        CollectionUtils.add(list, 8);
        CollectionUtils.add(list, 1);
        CollectionUtils.add(list, 3);
        CollectionUtils.addAll(Arrays.asList(5,6,4), list);
        System.out.println("After addAll:");
        list.forEach(l -> System.out.print(l + " "));
        System.out.println();

        CollectionUtils.removeAll(list, Arrays.asList(5,6,4));
        System.out.println("After removeAll:");
        list.forEach(l -> System.out.print(l + " "));
        System.out.println();

        boolean containsAll = CollectionUtils.containsAll(list, Arrays.asList(3,8,1));
        System.out.println("containsAll = " + containsAll);

        boolean containsAny = CollectionUtils.containsAny(list, Arrays.asList(10,16,3));
        System.out.println("containsAny = " + containsAny);

        list = CollectionUtils.range(Arrays.asList(8,1,3,5,6,4), 6, 8);
        list.forEach(l -> System.out.print(l + " "));
        System.out.println();

        list = CollectionUtils.range(Arrays.asList(8,1,3,5,6,4), 3, 6, ((o1, o2) -> o1 - o2));
        list.forEach(l -> System.out.print(l + " "));
        System.out.println();
    }
}
