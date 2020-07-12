package net.thumbtack.school.thread.Task9;

enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("Wrong first name! First name cannot be empty!"),
    TRAINEE_WRONG_LASTNAME("Wrong last name! Last name cannot be empty!"),
    TRAINEE_WRONG_RATING("Wrong rating! Rating must be from 1 to 5"),
    GROUP_WRONG_NAME("Wrong group name!"),
    GROUP_WRONG_ROOM("Wrong room name!"),
    TRAINEE_NOT_FOUND("Trainee not found!"),
    SCHOOL_WRONG_NAME("Wrong school name!"),
    DUPLICATE_GROUP_NAME("Duplicate without of group name!"),
    GROUP_NOT_FOUND("Group not found!"),
    EMPTY_TRAINEE_QUEUE("Queue is empty"),
    DUPLICATE_TRAINEE("Such trainee already exists!");

    private String errorCode;

    TrainingErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
