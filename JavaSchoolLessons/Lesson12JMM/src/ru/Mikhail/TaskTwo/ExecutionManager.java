package ru.Mikhail.TaskTwo;

import ru.Mikhail.TaskTwo.Context;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
