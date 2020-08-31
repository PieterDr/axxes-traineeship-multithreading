package com.axxes.traineeship.threading.completablefuture;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ExerciseCompletableFuture {

    private static final Random random = new Random();

    /*
     * This code tries to download 100 images. It is very slow however due to a lack of parallelization.
     * It also crashes when something goes wrong during a download.
     *
     * Use the CompletableFuture API to increase the performance and robustness of this piece of code.
     * We want to skip the images that fail to download (but log the error), as well as make this code run faster.
     * With some parallelization you should be able to make this program terminate in under a second.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<CompletableFuture<String>> downloadTasks = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> "https://www.images.com/" + i)
                .map(url -> CompletableFuture
                        .supplyAsync(() -> downloadImage(url))
                        .exceptionally(error -> {
                                    System.out.println(error.getMessage());
                                    return null;
                                }
                        ))
                .collect(toList());

        CompletableFuture.allOf(downloadTasks.toArray(new CompletableFuture[0]))
                .thenAccept(v -> downloadTasks.stream()
                        .map(CompletableFuture::join)
                        .filter(Objects::nonNull)
                        .forEach(System.out::println))
                .get();
    }

    private static String downloadImage(String url) {
        sleep(100);
        if (random.nextInt(10) < 1) {
            throw new RuntimeException("Something went wrong downloading: " + url);
        }
        return "Downloaded: " + url;
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
