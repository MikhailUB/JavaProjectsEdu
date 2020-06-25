package com.company;

import java.lang.reflect.*;
import java.util.HashMap;

public class Handler implements InvocationHandler {
    private Calculator calculator;
    private HashMap<CacheKey, Object> cache;

    public Handler(Calculator calculator) {
        this.calculator = calculator;
        cache = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {

        Cache annotation = method.getAnnotation(Cache.class);
        if (annotation == null)
            return method.invoke(calculator, args);

        CacheKey key = new CacheKey(method.getName(), (int)args[0]);
        Object result = cache.get(key);
        if (result == null) {
            result = method.invoke(calculator, args);
            cache.put(key, result);
        }
        else
            System.out.println("Из кэша " + key + " = " + result);

        return result;
    }
}

