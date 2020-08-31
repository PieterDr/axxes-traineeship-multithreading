package com.axxes.traineeship.threading.basicconcepts;

import java.util.concurrent.atomic.AtomicInteger;

public class ExerciseAtomicity {

    private static AtomicInteger counter = new AtomicInteger(0);

    /*
     * Atomic Variables
     *
     * Write a short program in which two threads both increment the counter variable 1,000,000 times,
     * without proper synchronisation. Print the result of at the end of the program.
     * It probably won't equal 2_000_000.
     *
     * Use an atomic variable to fix the problem
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> increment(1_000_000));
        Thread t1 = new Thread(() -> increment(1_000_000));

        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println("Count: " + counter);
    }

    private static void increment(int n) {
        for (int i = 0; i < n; i++) {
            counter.incrementAndGet();
        }
    }

    /*
     * Atomic Operations
     *
     * Check ExerciseAtomicityTest.java, normally the unit test should fail.
     * Introduce some form of synchronization in the code below to make it pass.
     */
    public static class CachingCharacterCounter {

        private String lastKey;
        private int lastValue;

        public int getNrOfCharacters(String input) {
            synchronized (this) {
                if (input.equalsIgnoreCase(lastKey)) {
                    return lastValue;
                }
            }

            int result = input.length();

            synchronized (this) {
                lastKey = input;
                lastValue = result;
            }
            return result;
        }
    }

}
