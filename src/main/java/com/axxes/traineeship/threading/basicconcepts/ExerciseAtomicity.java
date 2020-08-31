package com.axxes.traineeship.threading.basicconcepts;

public class ExerciseAtomicity {

    private static int counter = 0;

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
        // TODO implement
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
            if (input.equalsIgnoreCase(lastKey)) {
                return lastValue;
            }
            int result = input.length();
            lastKey = input;
            lastValue = result;
            return result;
        }
    }

}
