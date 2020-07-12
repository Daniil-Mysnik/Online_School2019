package net.thumbtack.school.springBootRest.exceptions;

public enum  RecordingErrorCode {
    WRONG_SONG("Song does not exists!"),
    WRONG_CAMPAIGN("Campaign does not exists!"),
    CAMPAIGN_STOPPED("Campaign stopped"),
    DUPLICATE_SONG("This song already exists"),
    SONG_DOES_NOT_EXIST("Song with this title does not exist"),
    CAMPAIGN_DOES_NOT_EXIST("Campaign for song does not exist");

    private String message;

    RecordingErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
