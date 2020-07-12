package net.thumbtack.school.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Task1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] A = {1, 2, 3, 4, 5, 6};
        int[] B = {8, 9, 10, 11, 12, 14};
        CompletableFuture<Double> avgA = CompletableFuture
                .supplyAsync(() -> {
                    double avg = 0;
                    for (int value : A) avg += value;
                    return avg / A.length;
                });
        CompletableFuture<Double> avgB = CompletableFuture
                .supplyAsync(() -> {
                    double avg = 0;
                    for (int value : B) avg += value;
                    return avg / B.length;
                });
        CompletableFuture<Double> upperSum = avgA.thenCombineAsync(avgB, (a, b) -> {
            double sum = 0;
            for (int i = 0; i < A.length; i++)
                sum += (A[i] - a) * (B[i] - b);
            return sum;
        });
        CompletableFuture<Double> lowerSum = avgA.thenCombineAsync(avgB, (a, b) -> {
            double sumA = 0;
            double sumB = 0;
            for (int value : A)
                sumA += Math.pow(value - a, 2);
            for (int value : B)
                sumB += Math.pow(value - b, 2);
            return sumA * sumB;
        });
        CompletableFuture<Double> correlation = upperSum.thenCombine(lowerSum, (a, b) -> a / Math.sqrt(b));
        System.out.println("Коэффицент корреляции - " + correlation.get());
    }

}
