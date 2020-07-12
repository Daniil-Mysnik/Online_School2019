package net.thumbtack.school.functional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Task15 {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Andrew", 15));
        persons.add(new Person("Vasya", 20));
        persons.add(new Person("Fedor", 25));
        persons.add(new Person("Ivan", 30));
        persons.add(new Person("Andrew", 35));
        persons.add(new Person("Andrew", 40));
        persons.add(new Person("Andrew", 45));

        List<String> sortedPersonsNames = new LinkedList<>();

        persons.stream().filter(person -> person.getAge() >= 30)
                .map(Person::getFirstName)
                .distinct()
                .sorted(Comparator.comparing(String::length))
                .forEach(System.out::println);
    }

}
