package net.thumbtack.school.thread.Task9;

public class TrainingException extends Exception {

    private TrainingErrorCode trainingErrorCode;

    public TrainingException(TrainingErrorCode trainingErrorCode) {
        this.trainingErrorCode = trainingErrorCode;
    }

    public TrainingErrorCode getErrorCode() {
        return trainingErrorCode;
    }

    public void setErrorCode(TrainingErrorCode trainingErrorCode) {
        this.trainingErrorCode = trainingErrorCode;
    }
}
