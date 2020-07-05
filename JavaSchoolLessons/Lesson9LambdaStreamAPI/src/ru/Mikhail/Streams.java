package ru.Mikhail;

import java.util.*;
import java.util.function.*;

public class Streams<T> {
    private List<T> list;

    private Streams(List<T> list)
    {
        //this.list = new ArrayList<>(list);
        this.list = list;
    }

    public static <T> Streams<T> of(List<T> list) {
        return new Streams<>(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        List<T> newList = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                newList.add(item);
            }
        }
        this.list = newList;
        return this;
    }

    public Streams<T> transform(Function<? super T, T> function) {
        List<T> newList = new ArrayList<>();
        for (T item : list) {
             newList.add(function.apply(item));
        }
        this.list = newList;
        return this;
    }

    public <K,V> Map<K, V> toMap(Function<? super T, K> fKey, Function<? super T, V> fValue) {
        Map<K, V> map = new HashMap<>();
        for (T item : list) {
            K key = fKey.apply(item);
            V value = fValue.apply(item);
            map.put(key, value);
        }
        return map;
    }
}

