package ru.Mikhail;

import java.util.concurrent.Executor;

public interface ThreadPool extends Executor {
    void start();
    void execute(Runnable runnable);
    void stop();
}
