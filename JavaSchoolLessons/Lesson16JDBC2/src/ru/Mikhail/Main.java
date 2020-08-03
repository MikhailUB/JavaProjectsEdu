package ru.Mikhail;

import ru.Mikhail.Database.*;
import ru.Mikhail.services.*;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        CacheMapDbSerializer<Double> dbSerializer = new CacheMapDbSerializer<>(
                "jdbc:postgresql://localhost:5432/test_db", "postgres", "1",
                "org.postgresql.Driver",
                new CacheMapDoubleMapper(),
                "cache");
        Service service = new ServiceImpl();
        Handler handler = new Handler(service, dbSerializer);

        Service proxy = (Service) Proxy.newProxyInstance(Service.class.getClassLoader(), new Class[] {Service.class}, handler);
        System.out.println(proxy.doHardWorkCacheDB("task1", 3));
        System.out.println(proxy.doHardWorkCacheDB("task1", 4));
        System.out.println(proxy.doHardWorkCacheDB("task1", 3));
        System.out.println(proxy.doHardWorkCacheDB("task3", 5));

        //System.out.println(proxy.doWork("task2", 3, "data"));
        //System.out.println(proxy.doWork("task2", 4, "data"));
        //System.out.println(proxy.doWork("task2", 3, "value"));
    }
}
