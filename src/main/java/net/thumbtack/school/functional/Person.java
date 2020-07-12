package net.thumbtack.school.functional;

import java.util.Objects;

public class Person {

    private String firstName;
    private Person mother, father;
    private int age;

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public Person(String firstName, Person mother, Person father) {
        this.firstName = firstName;
        this.mother = mother;
        this.father = father;
    }

    public Person(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }

    Person getMothersMotherFather() {
        if (mother == null) {
            return null;
        }
        if (mother.getMother() == null) {
            return null;
        }
        if (mother.getMother().getFather() == null) {
            return null;
        }
        return mother.getMother().getFather();
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(mother, person.mother) &&
                Objects.equals(father, person.father);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, mother, father, age);
    }

}
