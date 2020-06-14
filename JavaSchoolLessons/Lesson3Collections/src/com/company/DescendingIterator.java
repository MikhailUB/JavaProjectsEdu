package com.company;

import java.util.*;

public class DescendingIterator<T> implements Iterable<T> {
    private List<T> list;
    private int size;
    public DescendingIterator(List<T> list) {
        this.list = list;
        this.size = list.size();
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private int idx = size;
            @Override
            public boolean hasNext() {
                return 0 < idx && idx <= size;
            }

            @Override
            public T next() {
                idx--;
                return list.get(idx);
            }
        };
        return it;
    }
}
