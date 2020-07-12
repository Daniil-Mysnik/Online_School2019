package net.thumbtack.school.thread.Task9;

import java.util.*;

public class School {

    private String name;
    private int year;
    private Set<Group> groups;

    public School(String name, int year) throws TrainingException {
        setName(name);
        this.year = year;
        groups = Collections.synchronizedSet(new TreeSet<>(Comparator.comparing(group -> group.getName().compareTo(this.getName()))));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name.length() == 0) {
            throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        }
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public synchronized Set<Group> getGroups() {
        return groups;
    }

    public synchronized void addGroup(Group group) throws TrainingException {
        if (groups.contains(group)) {
            throw new TrainingException(TrainingErrorCode.DUPLICATE_GROUP_NAME);
        }
        groups.add(group);
    }

    public synchronized void removeGroup(Group group) throws TrainingException {
        if (!groups.remove(group)) {
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
        }
    }

    public synchronized void removeGroup(String name) throws TrainingException {
        if (!groups.removeIf(group -> group.getName().equals(name))) {
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
        }
    }

    public synchronized boolean containsGroup(Group group) {
        return groups.contains(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return year == school.year &&
                Objects.equals(name, school.name) &&
                Objects.equals(groups, school.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, groups);
    }
}