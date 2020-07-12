package net.thumbtack.school.springBootRest.exceptions;

public class RecordingException extends Exception {
    private RecordingErrorCode recordingErrorCode;

    public RecordingException(RecordingErrorCode recordingErrorCode) {
        this.recordingErrorCode = recordingErrorCode;
    }

    public RecordingErrorCode getRecordingErrorCode() {
        return recordingErrorCode;
    }

}
