package com.axxes.traineeship.threading.exercise;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.axxes.traineeship.threading.basicconcepts.ExerciseAtomicity.CachingCharacterCounter;
import static org.junit.jupiter.api.Assertions.fail;

class ExerciseAtomicityTest {

    @Test
    void characterCounterReturnsCorrectCount() throws InterruptedException {
        CachingCharacterCounter cache = new CachingCharacterCounter();
        AtomicBoolean cacheIsBroken = new AtomicBoolean(false);
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                if (cache.getNrOfCharacters("hello") != 5) {
                    cacheIsBroken.set(true);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                if (cache.getNrOfCharacters("world!") != 6) {
                    cacheIsBroken.set(true);
                }
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        if (cacheIsBroken.get()) fail();
    }
}
