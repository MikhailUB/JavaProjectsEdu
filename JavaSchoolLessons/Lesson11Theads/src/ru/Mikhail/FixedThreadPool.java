package ru.Mikhail;

import java.util.Queue;
import java.util.concurrent.*;

public class FixedThreadPool implements ThreadPool {
    protected final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
    protected final int threadsCount;
    protected volatile boolean isRunning = true;

    public FixedThreadPool(int threadsCount) {
        this.threadsCount = threadsCount;
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsCount; i++) {
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
