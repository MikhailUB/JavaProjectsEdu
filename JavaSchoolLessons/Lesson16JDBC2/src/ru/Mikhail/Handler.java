package ru.Mikhail;

import ru.Mikhail.Database.CacheMapDbSerializer;
import ru.Mikhail.annotations.*;
import ru.Mikhail.services.*;
import ru.Mikhail.utils.*;

import java.lang.reflect.*;

public class Handler implements InvocationHandler {
    private Service service;
    private CacheMap<Double> cache;
    private CacheMapDbSerializer dbSerializer;

    public Handler(Service service, CacheMapDbSerializer dbSerializer) {
        this.service = service;
        this.dbSerializer = dbSerializer;
        cache = new CacheMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {

        Cache annotation = method.getAnnotation(Cache.class);
        if (annotation == null)
            return method.invoke(service, args);

        String cacheFile = null;
        switch (annotation.cacheType()) {
            case FILE:
                cacheFile = annotation.fileName() != null && !annotation.fileName().isEmpty() ? annotation.fileName() : method.getName();
                CacheMap<Double> newCache = MySerializer.deserialize(cacheFile);
                if (newCache != null)
                    cache = newCache;
                break;
            case DATABASE:
                CacheMap<Double> dbCache = dbSerializer.deserialize();
                if (dbCache != null)
                    cache = dbCache;
                break;
        }

        CacheKey key = new CacheKey(method.getName(), (String)args[0], (int)args[1]);
        Double result = cache.get(key);
        if (result == null) {
            result = (double)method.invoke(service, args);
            cache.put(key, result);

            switch (annotation.cacheType()) {
                case FILE:
                    MySerializer.serialize(cacheFile, cache);
                    break;
                case DATABASE:
                    dbSerializer.serialize(cache);
                    break;
            }
        }
        else
            System.out.println("Из кэша " + key + " = " + result);

        return result;
    }
}