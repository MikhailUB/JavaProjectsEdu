package ru.Mikhail.TaskOne;

import java.util.concurrent.*;

public class Task<T> {
    private Callable<? extends T> callable;
    private volatile T result = null;
    private volatile TaskException exception = null;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if (exception != null)
            throw exception;

        if (result != null)
            return result;

        synchronized (this) {
            if (exception != null)
                throw exception;

            if (result != null)
                return result;

            ExecutorService service = Executors.newSingleThreadExecutor();
            Future<? extends T> future = service.submit(callable);
            try {
                result = future.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                exception = new TaskException(e);
                throw exception;
            }
            finally {
                service.shutdown();
            }
        }
        return result;
    }
}
