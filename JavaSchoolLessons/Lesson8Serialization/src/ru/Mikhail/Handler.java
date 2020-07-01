package ru.Mikhail;

import ru.Mikhail.annotations.*;
import ru.Mikhail.services.*;
import ru.Mikhail.utils.*;

import java.lang.reflect.*;

public class Handler implements InvocationHandler {
    private Service service;
    private CacheMap<Double> cache;

    public Handler(Service service) {
        this.service = service;
        cache = new CacheMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {

        Cache annotation = method.getAnnotation(Cache.class);
        if (annotation == null)
            return method.invoke(service, args);

        String cacheFile = null;
        if (annotation.cacheType() == CacheTypes.FILE) {
            cacheFile = annotation.fileName() != null && !annotation.fileName().isEmpty() ? annotation.fileName() : method.getName();
            CacheMap<Double> newCache = MySerializer.deserialize(cacheFile);
            if (newCache != null)
                cache = newCache;
        }

        CacheKey key = new CacheKey(method.getName(), (String)args[0], (int)args[1]);
        Double result = cache.get(key);
        if (result == null) {
            result = (double)method.invoke(service, args);
            cache.put(key, result);

            if (annotation.cacheType() == CacheTypes.FILE) {
                MySerializer.serialize(cacheFile, cache);
            }
        }
        else
            System.out.println("Из кэша " + key + " = " + result);

        return result;
    }
}