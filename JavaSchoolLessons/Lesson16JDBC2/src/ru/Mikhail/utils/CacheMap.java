package ru.Mikhail.utils;

import java.io.*;
import java.util.*;

public class CacheMap<T> implements Serializable {
    private Map<CacheKey, T> cache = new HashMap<>();

    public void put(CacheKey key, T value) {
        cache.put(key, value);
    }

    public T get(CacheKey key) {
        return cache.get(key);
    }

    public Set<Map.Entry<CacheKey, T>> entrySet() {
        return cache.entrySet();
    }
}
