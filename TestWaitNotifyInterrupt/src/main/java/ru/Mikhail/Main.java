package ru.Mikhail;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Store store = new Store();
        Consumer consumer = new Consumer(store);
        Producer producer = new Producer(store);
        Thread thread1 = new Thread(consumer);
        thread1.start();
        Thread thread2 = new Thread(producer);
        thread2.start();

        Thread.sleep(20000);
        thread1.interrupt();
        thread2.interrupt();
    }
}

class Consumer implements Runnable {
    Store store;
    public Consumer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                store.get();
            } catch (InterruptedException e) {
            }
        }
    }
}

class Producer implements Runnable{

    Store store;
    Producer(Store store){
        this.store=store;
    }
    public void run(){
        for (int i = 1; i < 10; i++) {
            try {
                store.put();
            } catch (InterruptedException e) {
            }
        }
    }
}

class Store {
    private int count = 0;

    public synchronized void get() throws InterruptedException {
        while (count < 1 && !Thread.currentThread().isInterrupted()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("get InterruptedException");
                Thread.currentThread().interrupt();
            }
        }
        if (Thread.currentThread().isInterrupted())
            return;

        Thread.sleep(1000);
        count--;
        System.out.println("Покупатель -1 всего: " + count);
        notify();
    }

    public synchronized void put() throws InterruptedException {
        while (count >= 3 && !Thread.currentThread().isInterrupted()) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.out.println("put InterruptedException");
                Thread.currentThread().interrupt();
            }
        }
        if (Thread.currentThread().isInterrupted())
            return;

        Thread.sleep(1000);
        count++;
        System.out.println("Производитель +1 всего: "+ count);
        notify();
    }
}

