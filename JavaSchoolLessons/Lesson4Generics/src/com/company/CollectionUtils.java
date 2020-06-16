package com.company;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/*
4. Параметризовать методы, используя правило PECS, и реализовать их
*/
public class CollectionUtils {
    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static List newArrayList() {
        throw new NotImplementedException();
    }

    public static int indexOf(List source, Object o) {
        throw new NotImplementedException();
    }

    public static List limit(List source, int size) {
        throw new NotImplementedException();
    }

    public static void add(List source, Object o) {
        throw new NotImplementedException();
    }

    public static void removeAll(List removeFrom, List c2) {
        throw new NotImplementedException();
    }

    //true если первый лист содержит все элементы второго
    public static boolean containsAll(List c1, List c2) {
        throw new NotImplementedException();
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static boolean containsAny(List c1, List c2) {
        throw new NotImplementedException();
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static List range(List list, Object min, Object max) {
        throw new NotImplementedException();
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static List range(List list, Object min, Object max, Comparator comparator) {
        throw new NotImplementedException();
    }

}
