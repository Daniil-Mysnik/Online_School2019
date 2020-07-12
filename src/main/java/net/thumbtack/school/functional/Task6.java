package net.thumbtack.school.functional;

import java.util.function.BiFunction;

public class Task6 {

    private static BiFunction<Integer, Integer, Integer> max = Math::max;

    public static void main(String[] args) {
        System.out.println(max.apply(1243, 534));
    }
}
