package com.axxes.traineeship.threading.buildingblocks;

import java.util.concurrent.CountDownLatch;

public class ExerciseLatches {

    private static Object resource = null;

    /*
     * Create a task in a thread that is dependent on the non-initialized resource.
     * Start the task and then initialize the resource (simulate that this takes some time).
     *
     * Use a CountDownLatch to ensure the task only starts when the resource has been initialized.
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread task = new Thread(() -> {
            try {
                System.out.println("Waiting for resource initialization...");
                countDownLatch.await();
                System.out.println("Resource: " + resource);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        task.start();
        resource = initializeResource(countDownLatch);
        task.join();
    }

    private static String initializeResource(CountDownLatch countDownLatch) throws InterruptedException {
        System.out.println("Initializing resource...");
        Thread.sleep(2000);
        countDownLatch.countDown();
        return "Resource Initialized";
    }
}
