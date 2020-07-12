package net.thumbtack.school.async;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Task2 {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<List<Integer>> readFirstFile = CompletableFuture
                .supplyAsync(() -> readFile("file1.txt"));

        CompletableFuture<List<Integer>> readSecondFile = CompletableFuture
                .supplyAsync(() -> readFile("file2.txt"));

        readFirstFile.thenCombineAsync(readSecondFile, Task2::getSum)
                .thenAccept(list -> writeToFile("file3.txt", list))
                .exceptionally(err -> {
                    System.out.println("An exception has occurred: " + err);
                    return null;
                });

        readFirstFile.thenCombineAsync(readSecondFile, Task2::getProduct)
                .thenAccept(list -> writeToFile("file4.txt", list))
                .exceptionally(err -> {
                    System.out.println("An exception has occurred: " + err);
                    return null;
                });

        Thread.sleep(1000);
    }

    private static List<Integer> readFile(String filename) {
        List<Integer> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            String line;
            while ((line = reader.readLine()) != null)
                result.add(Integer.parseInt(line));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static void writeToFile(String filename, List<Integer> list) {
        System.out.println("Write to file: " + filename);
        try (FileWriter writer = new FileWriter(filename, false)) {
            writer.write(list.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static List<Integer> getSum(List<Integer> f, List<Integer> s) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < f.size(); i++)
            res.add(f.get(i) + s.get(i));
        return res;
    }

    private static List<Integer> getProduct(List<Integer> f, List<Integer> s) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < f.size(); i++)
            res.add(f.get(i) * s.get(i));
        return res;
    }

}
