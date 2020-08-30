package com.axxes.traineeship.threading.intro;

public class ExerciseThreads {

    public static void main(String[] args) throws InterruptedException {
        creatingThreads();
        joiningThreads();
    }

    /*
     * Creating Threads
     *
     * Write a program that spawns a new Thread that prints 'Hello world!' preceded by the name of the thread
     * Do this in 2 ways:
     *    - pass the behaviour to the Thread constructor
     *    - extend the Thread class
     */
    private static void creatingThreads() {
        new Thread(() -> System.out.println(Thread.currentThread().getName() + ": Hello world!")).start();
        new HelloWorld().start();
    }

    private static class HelloWorld extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": Hello world!");
        }
    }

    /*
     * Joining threads
     * <p>
     * Write a program that spawns a new Thread that prints the numbers 1...100
     * When that thread is done, print 'Program finished'
     */
    private static void joiningThreads() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println(i);
            }
        });
        thread.start();
        thread.join();
        System.out.println("Program finished");
    }
}
