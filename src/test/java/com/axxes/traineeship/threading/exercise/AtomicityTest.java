package com.axxes.traineeship.threading.exercise;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtomicityTest {

    @RepeatedTest(100)
    void brokenCache() throws InterruptedException {
        Atomicity.BrokenCache cache = new Atomicity.BrokenCache();
        Thread t1 = new Thread(() -> {
            assertEquals(5, cache.getNrOfCharacters("hello"));
            assertEquals(10, cache.getNrOfCharacters("concurrent"));
            assertEquals(4, cache.getNrOfCharacters("java"));
            assertEquals(5, cache.getNrOfCharacters("world"));
            assertEquals(4, cache.getNrOfCharacters("lock"));
            assertEquals(4, cache.getNrOfCharacters("java"));
            assertEquals(5, cache.getNrOfCharacters("hello"));
            assertEquals(5, cache.getNrOfCharacters("world"));
            assertEquals(4, cache.getNrOfCharacters("lock"));
            assertEquals(4, cache.getNrOfCharacters("java"));
            assertEquals(10, cache.getNrOfCharacters("concurrent"));
            assertEquals(5, cache.getNrOfCharacters("world"));
            assertEquals(5, cache.getNrOfCharacters("hello"));
            assertEquals(6, cache.getNrOfCharacters("thread"));
            assertEquals(4, cache.getNrOfCharacters("lock"));
            assertEquals(5, cache.getNrOfCharacters("world"));
            assertEquals(10, cache.getNrOfCharacters("concurrent"));
        });
        Thread t2 = new Thread(() -> {
            assertEquals(5, cache.getNrOfCharacters("hello"));
            assertEquals(5, cache.getNrOfCharacters("hello"));
            assertEquals(5, cache.getNrOfCharacters("hello"));
            assertEquals(5, cache.getNrOfCharacters("hello"));
            assertEquals(5, cache.getNrOfCharacters("hello"));
            assertEquals(5, cache.getNrOfCharacters("world"));
            assertEquals(5, cache.getNrOfCharacters("world"));
            assertEquals(5, cache.getNrOfCharacters("world"));
            assertEquals(5, cache.getNrOfCharacters("world"));
            assertEquals(5, cache.getNrOfCharacters("world"));
            assertEquals(5, cache.getNrOfCharacters("world"));
            assertEquals(5, cache.getNrOfCharacters("world"));
            assertEquals(4, cache.getNrOfCharacters("lock"));
            assertEquals(4, cache.getNrOfCharacters("lock"));
            assertEquals(4, cache.getNrOfCharacters("lock"));
            assertEquals(4, cache.getNrOfCharacters("lock"));
            assertEquals(4, cache.getNrOfCharacters("lock"));
            assertEquals(4, cache.getNrOfCharacters("lock"));
            assertEquals(4, cache.getNrOfCharacters("java"));
            assertEquals(4, cache.getNrOfCharacters("java"));
            assertEquals(4, cache.getNrOfCharacters("java"));
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}