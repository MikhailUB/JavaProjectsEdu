package ru.Mikhail.services;

import ru.Mikhail.annotations.Cache;
import ru.Mikhail.annotations.CacheTypes;

public interface Service {
    @Cache(cacheType = CacheTypes.FILE, identityBy = { String.class, int.class })
    double doHardWork(String taskName, int value);

    @Cache(identityBy = { String.class, int.class})
    double doWork(String taskName, int value, String prefix);
}
