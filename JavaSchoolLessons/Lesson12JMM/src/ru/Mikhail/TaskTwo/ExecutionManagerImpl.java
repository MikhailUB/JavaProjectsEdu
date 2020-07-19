package ru.Mikhail.TaskTwo;

import java.util.*;
import java.util.concurrent.*;

public class ExecutionManagerImpl implements ExecutionManager, Context {
    private List<CompletableFuture<Void>> futures = new ArrayList<>();
    private Runnable callback;
    private ExecutorService service;
    private List<Runnable> canceleds;
    private Thread completeThread;

    public ExecutionManagerImpl() {
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        this.callback = callback;
        this.service = Executors.newFixedThreadPool(8);

        for (Runnable task : tasks) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(task, service);
            futures.add(future);
        }
        completeThread = new Thread(() -> checkAllComplete());
        completeThread.setDaemon(true);
        completeThread.start();

        return this;
    }

    private void checkAllComplete() {
        for (CompletableFuture<Void> future : futures) {
            try {
                future.join();
            }
            catch (CancellationException | CompletionException ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }
        if (!isFinished()) {
            service.shutdown();
            System.out.println("service.shutdown()");
        }
        invokeCallback();
    }

    private void invokeCallback() {
        // проверяем все ли завершилось
        /*
        while (!service.isShutdown()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
        */
        try {
            service.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        // вызваем callback
        if (callback != null)
            callback.run();
    }

    @Override
    public int getCompletedTaskCount() {
        return (int)futures.stream().filter(f -> f.isDone()).count();
    }

    @Override
    public int getFailedTaskCount() {
        return (int)futures.stream().filter(f -> f.isCompletedExceptionally()).count();
    }

    @Override
    public int getInterruptedTaskCount() {
        return canceleds == null ? 0 : canceleds.size();
    }

    @Override
    public void interrupt() {
        if (!isFinished()) {
            canceleds = service.shutdownNow();
            System.out.println("interrupt()");

            invokeCallback();
        }
    }

    @Override
    public boolean isFinished() {
        return service.isShutdown();
    }

}
