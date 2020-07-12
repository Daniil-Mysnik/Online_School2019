package net.thumbtack.school.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Task1234 {

    private static Function<String, List<String>> split = s -> Arrays.asList(s.split("\\s")); //Lambda
    // private static Function<List<?>, Integer> count = l -> l.size(); //Lambda
    private static Function<List<?>, Integer> count = List::size; //Method-reference

    public static void main(String[] args) {
        List<String> list = split.apply(new String("Some useless string"));
        list.forEach(System.out::println);
        System.out.println(count.apply(list));

        System.out.println(split.andThen(count).apply("test fourth task with andThen"));
        System.out.println(count.compose(split).apply("test fourth task with compose"));
    }

}
