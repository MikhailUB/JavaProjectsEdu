package com.company;

public class CacheKey {
    private String method;
    private int arg;

    public CacheKey(String method, int arg) {
        this.method = method;
        this.arg = arg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheKey)) return false;

        CacheKey cacheKey = (CacheKey) o;

        if (arg != cacheKey.arg) return false;
        return method.equals(cacheKey.method);
    }

    @Override
    public int hashCode() {
        return method.hashCode() ^ new Integer(arg).hashCode();
    }

    @Override
    public String toString() {
        return "CacheKey{" +
                "method='" + method + '\'' +
                ", arg=" + arg +
                '}';
    }
}
