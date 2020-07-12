package net.thumbtack.school.springBootRest.model;

import java.time.ZonedDateTime;

public class Campaign {
    Recording recording;
    ZonedDateTime time;
    boolean isActive;

    public Campaign() {
    }

    public Campaign(Recording recording, ZonedDateTime time, boolean isActive) {
        this.recording = recording;
        this.time = time;
        this.isActive = isActive;
    }

    public Recording getRecording() {
        return recording;
    }

    public void setRecording(Recording recording) {
        this.recording = recording;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
