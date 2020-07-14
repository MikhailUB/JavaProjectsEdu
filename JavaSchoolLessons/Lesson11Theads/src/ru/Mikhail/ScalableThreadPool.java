package ru.Mikhail;

public class ScalableThreadPool extends FixedThreadPool {
    private final int maxThreads;
    private int actualThreads;

    public ScalableThreadPool(int minThreads, int maxThreads) {
        super(minThreads);
        this.maxThreads = maxThreads;
        actualThreads = minThreads;
    }

    private synchronized void tryAddWorker() {
        if (actualThreads < maxThreads && actualThreads < workQueue.size()) {
            new Thread(new TaskWorker()).start();
            actualThreads++;
            System.out.println("actualThreads = " + actualThreads);
        }
    }

    private synchronized boolean canStopWorker() {
        if (actualThreads > threadsCount) {
            actualThreads--;
            System.out.println("actualThreads = " + actualThreads);
            return true;
        }
        return false;
    }

    @Override
    public void execute(Runnable command) {
        if (isRunning) {
            workQueue.offer(command);
            tryAddWorker();
        }
    }

    private final class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                Runnable newTask = workQueue.poll();
                if (newTask != null)
                    newTask.run();
                else if (canStopWorker())
                    return; // завершение worker-а и его потока
            }
        }
    }
}
