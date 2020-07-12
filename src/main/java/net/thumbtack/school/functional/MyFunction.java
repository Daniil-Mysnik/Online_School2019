package net.thumbtack.school.functional;

@FunctionalInterface
public interface MyFunction<K, T> {
    //K apply(T arg);
    K apply(T arg, T arg2);
}
//Функциональный интерфейс обязан иметь только один метод
