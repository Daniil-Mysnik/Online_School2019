package net.thumbtack.school.functional;

import java.util.function.Predicate;

public class Task8 {

    private static Predicate<Integer> isEven = a -> a != 0;

    public static void main(String[] args) {
        System.out.println(isEven.test(0));
        System.out.println(isEven.test(1021));
    }
}
