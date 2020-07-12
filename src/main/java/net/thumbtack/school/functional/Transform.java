package net.thumbtack.school.functional;

import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class Transform {

    public static void main(String[] args) {
        System.out.println("transform:");
        IntStream intStream1 = transform(IntStream.of(1,2,3,4,5), i -> i * i);
        intStream1.forEach(System.out::println);
        System.out.println("transform parallel:");
        IntStream intStream2 = transformParallel(IntStream.of(1,2,3,4,5), i -> i * i);
        intStream2.forEach(System.out::println);
    }

    public static IntStream transform(IntStream stream, IntUnaryOperator op) {
        return stream.map(op);
    }

    public static IntStream transformParallel(IntStream stream, IntUnaryOperator op) {
        return stream.parallel().map(op);
    }

}
