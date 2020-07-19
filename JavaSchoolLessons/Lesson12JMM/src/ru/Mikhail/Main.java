package ru.Mikhail;

import ru.Mikhail.TaskOne.*;
import ru.Mikhail.TaskTwo.*;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        //test1();
        test2();
    }

    private static void test1() {
        Callable<Double> c = () -> new Counter().count(100);
        Task<Double> task = new Task<Double>(c);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                Double res = task.get();
                System.out.println("thread" + finalI + " res=" + res);
            }).start();
        }
    }

    private static void test2() {
        Counter counter = new Counter();
        Runnable[] arr = new Runnable[50];
        for (int i = 0; i < arr.length; i++) {
            int finalI = i;
            arr[i] = () -> counter.count(finalI);
        }

        ExecutionManager manager = new ExecutionManagerImpl();
        Context context = manager.execute(Main::callback, arr);

        //while (!context.isFinished()) { // для проверки штатного завершения всех задач
        for (int i = 0; i < 2; i++) { // для проверки interrupt()
            System.out.println("CompletedTaskCount = " + context.getCompletedTaskCount());
            System.out.println("FailedTaskCount = " + context.getFailedTaskCount());
            System.out.println("InterruptedTaskCount = " + context.getInterruptedTaskCount());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }

        context.interrupt();
        System.out.println("CompletedTaskCount = " + context.getCompletedTaskCount());
        System.out.println("FailedTaskCount = " + context.getFailedTaskCount());
        System.out.println("InterruptedTaskCount = " + context.getInterruptedTaskCount());
    }

    private static void callback() {
        System.out.println("I am callback");
    }
}
