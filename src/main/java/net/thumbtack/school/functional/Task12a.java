package net.thumbtack.school.functional;

public class Task12a {

    public static void main(String[] args) {
        Person greatGreatGrandFather = new Person("great-great-grandfather", null, null);
        Person grandMother = new Person("grandMother", null, greatGreatGrandFather);
        Person mother = new Person("mother", grandMother, null);
        Person person = new Person("person", mother, null);

        if (person.getMothersMotherFather() == null) {
            System.out.println(person.getMothersMotherFather());
        } else System.out.println(person.getMothersMotherFather().getFirstName());
    }

}
