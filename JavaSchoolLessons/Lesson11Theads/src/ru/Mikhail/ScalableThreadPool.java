package ru.Mikhail;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ScalableThreadPool implements ThreadPool {
    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
    private final int minThreads;
    private final int maxThreads;
    private volatile boolean isRunning = true;

    public ScalableThreadPool(int minThreads, int maxThreads) {
        this.minThreads = minThreads;
        this.maxThreads = maxThreads;
    }

    @Override
    public boolean hasTasks() {
        return !workQueue.isEmpty();
    }

    @Override
    public void start() {
        for (int i = 0; i < minThreads; i++) {
            new Thread(new TaskWorker()).start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if (isRunning)
            workQueue.offer(command);
    }

    @Override
    public void  stop() {
        isRunning = false;
    }

    private final class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                Runnable newTask = workQueue.poll();
                if (newTask != null)
                    newTask.run();
            }
        }
    }
}
