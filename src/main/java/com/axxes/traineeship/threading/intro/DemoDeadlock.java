package com.axxes.traineeship.threading.intro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Threads are stuck waiting for a resource
 */
public class DemoDeadlock {

    private static final Lock l1 = new ReentrantLock();
    private static final Lock l2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> {
            try {
                l1.lock();
                System.out.println(Thread.currentThread().getName() + ": acquired lock 1");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + ": trying to acquire lock 2...");
                l2.lock();
                System.out.println(Thread.currentThread().getName() + ": acquired lock 2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t1 = new Thread(() -> {
            try {
                l2.lock();
                System.out.println(Thread.currentThread().getName() + ": acquired lock 2");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + ": trying to acquire lock 1...");
                l1.lock();
                System.out.println(Thread.currentThread().getName() + ": acquired lock 1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Starting threads...");

        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println("Threads finished");
    }
}
