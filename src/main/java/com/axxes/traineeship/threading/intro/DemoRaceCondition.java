package com.axxes.traineeship.threading.intro;

/**
 * Multiple threads acting on a shared variable results in unpredictable outcome if not properly managed
 */
public class DemoRaceCondition {

    private static String result = "";

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t0 = new Thread(() -> append("a", 100));
            Thread t1 = new Thread(() -> append("b", 100));

            t0.start();
            t1.start();

            t0.join();
            t1.join();

            System.out.println("Result: " + result);
            result = "";
        }
    }

    private static void append(String s, int n) {
        for (int i = 0; i < n; i++) {
            try {
                result += s;
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
