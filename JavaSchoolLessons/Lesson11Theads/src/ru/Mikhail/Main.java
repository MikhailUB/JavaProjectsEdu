package ru.Mikhail;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new FixedThreadPool(8);
        threadPool.start();
        Counter counter = new Counter();

        for (int i = 0; i < 400; i++) {
            final int j = i;
            threadPool.execute(() -> counter.count(j));
        }

        while (threadPool.hasTasks())
            Thread.sleep(500);

        threadPool.stop();
    }
}
