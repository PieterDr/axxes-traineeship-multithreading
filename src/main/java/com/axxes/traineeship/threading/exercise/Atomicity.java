package com.axxes.traineeship.threading.exercise;

public class Atomicity {

    // Simple synchronisation
    //
    // Write a short program in which two threads both increment a shared
    // integer repeatedly, without proper synchronisation, 1,000,000 times,
    // printing the result at the end of the program.
    //
    // Now modify the program to use synchronisation to ensure that increments
    // on the shared variable are atomic.
    public static void main(String[] args) {

    }

    //TODO make test class succeed
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
