package com.axxes.traineeship.threading.executors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ExerciseExecutors {

    static class ExecutorServiceExercise {

        /*
         * Write a program that submits some tasks to an ExecutorService.
         * Also submit some tasks that return a value. Print the value when it becomes available.
         */
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            ExecutorService executor = Executors.newFixedThreadPool(4);

            Future<?> task1 = executor.submit(() -> System.out.println(Thread.currentThread().getName() + ": task 1"));
            Future<?> task2 = executor.submit(() -> System.out.println(Thread.currentThread().getName() + ": task 2"));
            Future<String> task3 = executor.submit(() -> Thread.currentThread().getName() + ": task 3");
            Future<String> task4 = executor.submit(() -> Thread.currentThread().getName() + ": task 4");

            task1.get();
            task2.get();
            System.out.println(task3.get());
            System.out.println(task4.get());

            executor.shutdown();
        }
    }

    static class CompletionServiceExercise {

        /*
         * Again create some tasks that return a value.
         * This time make use of a CompletionService to retrieve the values.
         */
        public static void main(String[] args) throws InterruptedException, ExecutionException {
            ExecutorService executor = Executors.newFixedThreadPool(4);
            ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executor);

            completionService.submit(() -> Thread.currentThread().getName() + ": task 1");
            completionService.submit(() -> Thread.currentThread().getName() + ": task 2");
            completionService.submit(() -> Thread.currentThread().getName() + ": task 3");
            completionService.submit(() -> Thread.currentThread().getName() + ": task 4");

            for (int i = 0; i < 4; i++) {
                System.out.println(completionService.take().get());
            }

            executor.shutdown();
        }
    }

    static class TaskLifecycleHooks {

        /*
         * Extend the ThreadPoolExecutor class to create an executor that times
         * and prints the duration of submitted tasks.
         */
        public static void main(String[] args) {
            TimingExecutor executor = new TimingExecutor();

            executor.submit(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            executor.submit(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            executor.submit(() -> {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            executor.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            executor.shutdown();
        }

        static class TimingExecutor extends ThreadPoolExecutor {

            private final Map<Runnable, Long> threadTimerMap = new HashMap<>();

            public TimingExecutor() {
                super(2, 2, 0L, SECONDS, new ArrayBlockingQueue<>(4));
            }

            @Override
            protected void beforeExecute(Thread thread, Runnable runnable) {
                System.out.println(Thread.currentThread().getName() + ": starting task...");
                threadTimerMap.put(runnable, System.currentTimeMillis());
            }

            @Override
            protected void afterExecute(Runnable runnable, Throwable t) {
                long taskDuration = System.currentTimeMillis() - threadTimerMap.get(runnable);
                System.out.println(Thread.currentThread().getName() + ": task took " + taskDuration + "ms");
                threadTimerMap.remove(runnable);
            }
        }
    }
}
