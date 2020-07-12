package ru.Mikhail;

public interface ThreadPool {
    void start();
    void execute(Runnable runnable);
    void stop();
    boolean hasTasks();
}
