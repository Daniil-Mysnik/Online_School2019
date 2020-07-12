package net.thumbtack.school.functional;

import java.util.function.BiPredicate;

public class Task9 {

    private static BiPredicate<Integer, Integer> areEqual = Integer::equals;

    public static void main(String[] args) {
        System.out.println(areEqual.test(10, 10));
        System.out.println(areEqual.test(9, 10));
    }
}
