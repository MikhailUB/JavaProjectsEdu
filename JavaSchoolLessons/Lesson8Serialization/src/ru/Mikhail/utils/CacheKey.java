package ru.Mikhail.utils;

import java.io.Serializable;

public class CacheKey implements Serializable {
    private String method;
    private String strArg;
    private int intArg;

    public CacheKey(String method, String strArg, int intArg) {
        this.method = method;
        this.strArg = strArg;
        this.intArg = intArg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheKey)) return false;

        CacheKey oKey = (CacheKey) o;

        return intArg == oKey.intArg && strArg.equals(oKey.strArg) && method.equals(oKey.method);
    }

    @Override
    public int hashCode() {
        return strArg.hashCode() ^ method.hashCode() ^ new Integer(intArg).hashCode();
    }

    @Override
    public String toString() {
        return "CacheKey{" +
                "method='" + method + '\'' +
                ", strArg='" + strArg + '\'' +
                ", intArg=" + intArg +
                '}';
    }
}
