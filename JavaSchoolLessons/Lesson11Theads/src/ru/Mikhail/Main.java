package ru.Mikhail;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //testThreadPool(new FixedThreadPool(8));

        testThreadPool(new ScalableThreadPool(8, 16));
    }

    private static void testThreadPool(ThreadPool threadPool) throws InterruptedException, ExecutionException{
        threadPool.start();
        Counter counter = new Counter();

        List<Future<Double>> futures = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            final int j = i;
            CompletableFuture<Double> complFuture = CompletableFuture.supplyAsync(() -> counter.count(j), threadPool);
            futures.add(complFuture);
            if (i % 50 == 0)
                Thread.sleep(3600);
        }

        double value = 0;
        for (Future<Double> future : futures) {
            value += future.get();
        }

        threadPool.stop();
    }
}
