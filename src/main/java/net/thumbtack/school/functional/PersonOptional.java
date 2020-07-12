package net.thumbtack.school.functional;

import java.util.Objects;
import java.util.Optional;

public class PersonOptional {

    String firstName;
    Optional<PersonOptional> mother, father;

    public PersonOptional(String firstName, PersonOptional mother, PersonOptional father) {
        this.firstName = firstName;
        this.mother = Optional.ofNullable(mother);
        this.father = Optional.ofNullable(father);
    }

    Optional<PersonOptional> getMothersMotherFather() {
        return Optional.of(this)
                .flatMap(PersonOptional::getMother)
                .flatMap(PersonOptional::getMother)
                .flatMap(PersonOptional::getFather);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Optional<PersonOptional> getMother() {
        return mother;
    }

    public void setMother(PersonOptional mother) {
        this.mother = Optional.ofNullable(mother);
    }

    public Optional<PersonOptional> getFather() {
        return father;
    }

    public void setFather(PersonOptional father) {
        this.father = Optional.ofNullable(father);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonOptional that = (PersonOptional) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(mother, that.mother) &&
                Objects.equals(father, that.father);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, mother, father);
    }

}
