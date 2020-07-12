package net.thumbtack.school.springBootRest.dto.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RecordingRequest {
    @NotNull
    private String artist;
    @NotNull
    private String songTitle;
    @NotNull
    private String albumTitle;
    @Min(value = 1970, message = "Min value: 1970")
    private Integer yearOfIssue;
    @NotNull
    private String coverPath;
    @NotNull
    private String genre;
    @NotNull
    private String duration;
    @NotNull
    private String audioPath;
    private String videoPath;

    public RecordingRequest() {
    }

    public RecordingRequest(@NotNull String artist, @NotNull String songTitle, @NotNull String albumTitle, @Digits(integer = 1970, fraction = 0, message = "Min value: 1970") Integer yearOfIssue, @NotNull String coverPath, @NotNull String genre, @NotNull String duration, @NotNull String audioPath, String videoPath) {
        this.artist = artist;
        this.songTitle = songTitle;
        this.albumTitle = albumTitle;
        this.yearOfIssue = yearOfIssue;
        this.coverPath = coverPath;
        this.genre = genre;
        this.duration = duration;
        this.audioPath = audioPath;
        this.videoPath = videoPath;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(Integer yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

}
