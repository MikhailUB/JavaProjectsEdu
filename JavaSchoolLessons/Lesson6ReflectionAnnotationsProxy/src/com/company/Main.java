package com.company;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/*
1. Вывести на консоль все методы класса, включая все родительские методы (включая приватные)
2. Вывести все геттеры класса
3. Проверить что все String константы имеют значение = их имени
public static final String MONDAY = "MONDAY";
4. Реализовать кэширующий proxy
Кэширующий прокси перехватывает вызовы интерфейса.
Если метод помечен аннотацией @Cache, то:
Проверяет есть ли в кэше результат, если есть, то возвращает его.
Иначе, вызывает реальный метод, кэширует результат и возвращает его.
Если метод не помечен аннотацей @Cache, просто делегирует метод реализации
*/
public class Main {

    public static void main(String[] args) {
        //task1();
        task2();
    }

    // 1. Вывести на консоль все методы класса, включая все родительские методы (включая приватные)
    private static void task1() {
        System.out.println("1. Выводим методы свои и родительские");
        ChildClass child = new ChildClass("Михаил");
        printMethods(child.getClass());
        Class<?> superClazz = child.getClass().getSuperclass();
        printMethods(superClazz);
    }

    private static <T> void printMethods(Class<T> clazz) {
        System.out.println(clazz);
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method.getName());
        }
    }

    private static void task2() {
        System.out.println("2. Выводим геттеры");
        BaseParent parent = new BaseParent("Михаил");
        Class<? extends BaseParent> clazz = parent.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : clazz.getDeclaredFields()) {
            try {
                Method method = clazz.getDeclaredMethod("get" + firstUpperCase(field.getName()));
                System.out.println(method.getName());
            } catch (NoSuchMethodException e) {
            }
        }
    }

    public static String firstUpperCase(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
