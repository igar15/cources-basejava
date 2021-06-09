package ru.igar15.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static final Object LOCK = new Object();
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        }.start();
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        thread.start();
        System.out.println(thread.getState());

        Thread daemon = new Thread(() -> {while (true) {}});
        daemon.setDaemon(true);
        daemon.start();

        List<Thread> threads = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            Thread incThread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    inc2();
                }
            });
            incThread.start();
            threads.add(incThread);
        }

        for (Thread temp : threads) {
            temp.join();
        }

//        Thread.sleep(8000);
        System.out.println(counter);
    }

    private static synchronized void inc() {
        counter++;
    }

    private static void inc2() {
        synchronized (LOCK) {
            counter++;
        }
    }
}
