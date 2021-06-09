package ru.igar15.webapp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class MainConcurrency2 {
    private static int counter = 0;
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();


        Thread supplier = new Thread(() -> {
            while (counter < 10) {
                String uuid = UUID.randomUUID().toString();
                System.out.println("Supplier adds uuid to queue: " + uuid);
                synchronized (queue) {
                    counter++;
                    queue.add(uuid);
                    queue.notify();
                    try {
                        queue.wait(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (counter < 10) {
                synchronized (queue) {
                    String uuid = queue.poll();
                    System.out.println("Consumer gets uuid from queue: " + uuid);
                    queue.notify();
                    try {
                        queue.wait(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        consumer.start();
        supplier.start();
    }
}
