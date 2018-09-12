package com.axxes.traineeship.threading.exercise;

import java.util.concurrent.ThreadPoolExecutor;

public class Threads {

    // Creating and joining threads
    //
    // Write a short program that prints "Hello world" from a thread.
    //
    // Now modify the program to print "Hello world" five times, once from each
    // of five different threads.
    //
    // Now modify the printed string to include the thread number; ensure that
    // all threads have a unique thread number.
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        t1.start();
        t1.join();
        System.out.println("TEST");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
