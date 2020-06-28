package com.company;

import java.lang.reflect.*;

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

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        //task1();
        //task2();
        task3();
        task4();
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

    // 2. Вывести все геттеры класса
    private static void task2() {
        System.out.println("2. Выводим геттеры");
        BaseParent parent = new BaseParent("Михаил");
        Class<? extends BaseParent> clazz = parent.getClass();

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

    // 3. Проверить что все String константы имеют значение = их имени
    // public static final String MONDAY = "MONDAY";
    private static void task3() throws ClassNotFoundException, IllegalAccessException {
        System.out.println("3. проверяем String константы");
        ChildClass child = new ChildClass("Михаил");
        Class<? extends ChildClass> clazz = child.getClass();

        System.out.println("Класс " + clazz.getSimpleName() + " имеет строковые константы:");
        for (Field field : clazz.getFields()) {
            int modifiers = field.getModifiers();
            if ((modifiers & Modifier.FINAL) != 0 && field.getType() == String.class) {
                String fName = field.getName();
                String fValue = (String)field.get(child);
                String equal = fName.equals(fValue) ? " == " : " != ";
                System.out.println("Константа " + fName + equal + fValue);
            }
        }
    }

    // 4. Реализовать кэширующий proxy
    private static void task4()
    {
        Calculator calculator = new CalculatorImpl();
        Handler handler = new Handler(calculator);
        Calculator proxy = (Calculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(), new Class[] { Calculator.class }, handler);
        System.out.println(proxy.Plus(18));
        System.out.println(proxy.Minus(18));
        System.out.println(proxy.Plus(18));
        System.out.println(proxy.Minus(18));
        System.out.println(proxy.Plus(15));
        System.out.println(proxy.Minus(15));
    }
}
