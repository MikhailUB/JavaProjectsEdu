package ru.Mikhail;

import java.util.*;
/*
Заполняем память объектами до OutOfMemoryException
Исследуем структуру памяти и информацию от сборщика мусора
с помощью утилиты Oracle "Java Mission Control" jmc.exe
Виды сборщиков мусора:
Serial (последовательный)
Parallel (параллельный)
Concurrent Mark Sweep (CMS)
Garbage-First (G1)
*/
public class GarbageProducer {
    static List<Object> l;

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30_000);
        l = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            l.add(new Object());
        }
    }
}
