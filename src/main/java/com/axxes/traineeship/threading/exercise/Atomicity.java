package com.axxes.traineeship.threading.exercise;

public class Atomicity {

    // Simple synchronisation
    //
    // Write a short program in which two threads both increment a shared
    // integer repeatedly, without proper synchronisation, 1,000,000 times,
    // printing the result at the end of the program.
    //
    // Now use an atomic variable to fix the problem
    public static void main(String[] args) {

    }

    //TODO make unit test pass (AtomicityTest.java)
    static class BrokenCache {

        private String lastKey;
        private int lastValue;

        int getNrOfCharacters(String input) {
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
