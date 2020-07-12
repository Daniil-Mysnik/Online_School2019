package net.thumbtack.school.thread.Task9;

import java.util.*;

public class  Group {
    private String name;
    private String room;
    private List<Trainee> trainees;

    public Group(String name, String room) throws TrainingException {
        setName(name);
        setRoom(room);
        trainees = Collections.synchronizedList(new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name.length() == 0) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        }
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if (room == null || room.length() == 0) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        }
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public synchronized void addTrainee(Trainee trainee) {
        trainees.add(trainee);
    }

    public synchronized void removeTrainee(Trainee trainee) throws TrainingException {
        if (!trainees.remove(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public synchronized void removeTrainee(int index) throws TrainingException {
        if (trainees.size() <= index || index < 0) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        trainees.remove(index);
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        for (Trainee trainee : trainees) {
            if (trainee.getFirstName().equals(firstName)) {
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        for (Trainee trainee : trainees) {
            if (trainee.getFullName().equals(fullName)) {
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public synchronized void sortTraineeListByFirstNameAscendant() {
        trainees.sort(Comparator.comparing(Trainee::getFirstName));
    }

    public synchronized void sortTraineeListByRatingDescendant() {
        trainees.sort(Comparator.comparing(Trainee::getRating).reversed());
    }

    public synchronized void reverseTraineeList() {
        Collections.reverse(trainees);
    }


    public synchronized void rotateTraineeList(int positions) {
        Collections.rotate(trainees, positions);
    }

    public synchronized List<Trainee> getTraineesWithMaxRating() throws TrainingException {
        int numberOfTraineesWithMaxRating = 0;
        if (trainees.isEmpty()) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        sortTraineeListByRatingDescendant();
        Iterator<Trainee> iterator = trainees.iterator();
        while (iterator.next().getRating() == trainees.get(0).getRating()) {
            numberOfTraineesWithMaxRating++;
        }
        return trainees.subList(0, numberOfTraineesWithMaxRating);
    }

    public synchronized boolean hasDuplicates() {
        return new HashSet<>(trainees).size() != trainees.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(room, group.room) &&
                Objects.equals(trainees, group.trainees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, trainees);
    }
}
