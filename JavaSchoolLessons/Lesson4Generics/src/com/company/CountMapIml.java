package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CountMapIml<T> implements CountMap<T> {

    private Map<T, Integer> mapData = new HashMap<>();

    // добавляет элемент в контейнер
    @Override
    public void add(T o) {
        mapData.put(o, getCount(o) + 1);
        //HashMap<String, Integer> hashMap = new HashMap<>();
        //hashMap.put();
    }

    //Возвращает количество добавлений данного элемента
    @Override
    public int getCount(T o) {
        return mapData.getOrDefault(o, 0);
    }

    //Удаляет элемент и контейнера и возвращает количество его добавлений (до удаления)
    @Override
    public int remove(T o) {
        return remove(o);
    }

    //количество разных элементов
    @Override
    public int size() {
        return mapData.size();
    }

    //Добавить все элементы из source в текущий контейнер, при совпадении ключей, суммировать значения
    @Override
    public void addAll(CountMap<? extends T> source) {
        source.toMap().forEach((k, v) -> mapData.merge((T)k, (Integer) v, (oldV, newV) -> oldV + newV));
    }

    //Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
    @Override
    public Map<T, Integer> toMap() {
        Map<T, Integer> map = mapData;
        return map;
    }

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.clear();
        mapData.forEach((k, v) -> destination.put(k, v));
    }
}
