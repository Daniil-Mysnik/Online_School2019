package net.thumbtack.school.functional;

public class Task12b {

    public static void main(String[] args) {
        PersonOptional greatGreatGrandFather = new PersonOptional("great-great-grandfather", null, null);
        PersonOptional grandMother = new PersonOptional("grandMother", null, greatGreatGrandFather);
        PersonOptional mother = new PersonOptional("mother", grandMother, null);
        PersonOptional person = new PersonOptional("person", mother, null);

        System.out.println(person.getMothersMotherFather().get().firstName);
    }

}
