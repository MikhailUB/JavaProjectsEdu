package com.company;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/*
4. Параметризовать методы, используя правило PECS, и реализовать их
*/
public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<T> limit(List<T> source, int size) {
        return source.subList(0, size - 1);
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    //true если первый лист содержит все элементы второго
    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for (T o: c2) {
            if (c1.contains(o)) {
                return true;
            }
        }
        return false;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6,4), 3, 6) вернет {3,4,5,6}
    public static <T> List<T> range(List<? extends T> list, T min, T max) {
        List<T> res = newArrayList();
        for (T o: list) {
            Comparable<T> oComp = (Comparable<T>)o;
            if (oComp.compareTo(min) >= 0 && oComp.compareTo(max) <= 0) {
                res.add(o);
            }
        }
        return res;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparator.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<T> comparator) {
        List<T> res = newArrayList();
        for (T o: list) {
            if (comparator.compare(o, min) >= 0 && comparator.compare(o, max) <= 0) {
                res.add(o);
            }
        }
        return res;
    }

}
