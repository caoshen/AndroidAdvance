package io.github.caoshen.androidadvance.java8inaction.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Completable {

    public void complete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> " world");

        CompletableFuture<String> combinedFuture = future1.thenCompose(result1 -> future2.thenApply(result2 -> result1 + result2));
        String combinedResult = combinedFuture.get();
        System.out.println(combinedResult);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Completable().complete();
    }
}
