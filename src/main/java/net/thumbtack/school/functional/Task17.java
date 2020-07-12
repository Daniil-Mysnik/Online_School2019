package net.thumbtack.school.functional;

import java.util.ArrayList;
import java.util.List;

public class Task17 {

    private static Integer sum(List<Integer> list) {
        return list.stream().reduce((a, b) -> a + b).get();
    }

    private static Integer product(List<Integer> list) {
        return list.stream().reduce((a, b) -> Math.max(a, b)).get();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(sum(list));
        System.out.println(product(list));
    }
}
