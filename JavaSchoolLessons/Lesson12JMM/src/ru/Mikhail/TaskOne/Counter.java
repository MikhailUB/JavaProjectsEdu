package ru.Mikhail.TaskOne;

public class Counter {
    public Double count(double a) {
        double res = a;
        for (int i = 0; i < 1000000; i++) {
            res += Math.tan(res);
        }
        //throw new Exception("Типа ошибка");
        System.out.println("count " + a + " = " + res);
        return res;
    }
}
