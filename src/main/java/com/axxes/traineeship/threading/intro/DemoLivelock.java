package com.axxes.traineeship.threading.intro;

/**
 * Both worker threads seem busy but no progress is made...
 */
public class DemoLivelock {

    private static boolean worker1Active = false;
    private static boolean worker2Active = false;

    public static void main(String[] args) throws InterruptedException {
        Thread worker1 = new Thread(() -> {
            try {
                worker1Active = true;
                Thread.sleep(1000);
                while (worker2Active) {
                    System.out.println(Thread.currentThread().getName() + ": Other worker active, waiting for my turn...");
                    Thread.sleep(500);
                }
                doWorkOnSharedResource();
                worker1Active = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread worker2 = new Thread(() -> {
            try {
                worker2Active = true;
                while (worker1Active) {
                    System.out.println(Thread.currentThread().getName() + ": Other worker active, waiting for my turn...");
                    Thread.sleep(500);
                }
                doWorkOnSharedResource();
                worker2Active = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Starting threads...");

        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();

        System.out.println("Threads finished");
    }

    private static void doWorkOnSharedResource() {
        // lots of work...
    }
}
