package net.thumbtack.school.functional;

import java.util.*;
import java.util.stream.Collectors;

public class Task16 {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Andrew", 15));
        persons.add(new Person("Vasya", 20));
        persons.add(new Person("Fedor", 25));
        persons.add(new Person("Ivan", 30));
        persons.add(new Person("Andrew", 35));
        persons.add(new Person("Andrew", 40));
        persons.add(new Person("Andrew", 45));

        persons.stream()
                .filter(person -> person.getAge() >= 30)
                .collect(Collectors.groupingBy(Person::getFirstName, Collectors.counting()))
                .entrySet()
                .stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .map(Map.Entry::getKey)
                    .forEach(System.out::println);
    }

}
