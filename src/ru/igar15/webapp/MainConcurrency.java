package ru.igar15.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    //    private static final Object LOCK = new Object();
    private static final Lock lock = new ReentrantLock();
    private static final int THREADS_NUMBER = 10000;
    private static int counter;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
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

        CountDownLatch countDownLatch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> counterFuture = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    inc2();
                }
                countDownLatch.countDown();
                return counter;
            });
//            Thread incThread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    inc2();
//                }
//                countDownLatch.countDown();
//            });
//            incThread.start();
//            threads.add(incThread);
        }

//        for (Thread temp : threads) {
//            temp.join();
//        }
//        countDownLatch.await();
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(counter);
    }

    private static synchronized void inc() {
        counter++;
    }

    private static void inc2() {
//        synchronized (LOCK) {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
//        }
    }
}
