package ru.Mikhail.services;

import ru.Mikhail.services.*;

public class ServiceImpl implements Service {
    @Override
    public double doHardWork(String taskName, int value) {
        return Math.pow(value, value);
    }

    @Override
    public double doWork(String taskName, int value, String prefix) {
        final int ten = 10;
        return value * ten;
    }

    @Override
    public double doHardWorkCacheDB(String taskName, int value) {
        for (int i = 0; i < 1000000; i++) {
            if (i % 2 == 0)
                value += i;
            else
                value -= i;
        }
        return value;
    }

}
