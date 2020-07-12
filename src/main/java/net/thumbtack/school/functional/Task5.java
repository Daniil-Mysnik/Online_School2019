package net.thumbtack.school.functional;

import java.util.function.Function;

public class Task5 {

    //private static Function<String, Person> create = s -> new Person(s); //Lambda
    private static Function<String, Person> create = Person::new; //Method-reference

    public static void main(String[] args) {
        Person person = create.apply("Daniil");
        System.out.println(person.getFirstName());
    }
}
