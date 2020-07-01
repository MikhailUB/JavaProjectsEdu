package ru.Mikhail;

import ru.Mikhail.services.*;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        Service service = new ServiceImpl();
        Handler handler = new Handler(service);

        Service proxy = (Service) Proxy.newProxyInstance(Service.class.getClassLoader(), new Class[] {Service.class}, handler);
        System.out.println(proxy.doHardWork("task1", 3));
        System.out.println(proxy.doHardWork("task1", 4));
        System.out.println(proxy.doHardWork("task1", 3));

        System.out.println(proxy.doWork("task2", 3, "data"));
        System.out.println(proxy.doWork("task2", 4, "data"));
        System.out.println(proxy.doWork("task2", 3, "value"));
    }
}
