package com.axxes.traineeship.threading.exercise;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AtomicityTest {

    @Test
    void brokenCache() throws InterruptedException {
        Atomicity.BrokenCache cache = new Atomicity.BrokenCache();
        AtomicBoolean error = new AtomicBoolean(false);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    assertEquals(5, cache.getNrOfCharacters("hello"));
                } catch (AssertionFailedError ex) {
                    error.set(true);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    assertEquals(6, cache.getNrOfCharacters("world!"));
                } catch (AssertionFailedError ex) {
                    error.set(true);
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        if (error.get()) fail();
    }
}
