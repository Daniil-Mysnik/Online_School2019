package net.thumbtack.school.springBootRest.model;

import java.util.Objects;

public class Recording {
    private String artist;
    private String songTitle;
    private String albumTitle;
    private Integer yearOfIssue;
    private String coverPath;
    private String genre;
    private String duration;
    private String audioPath;
    private String videoPath;

    public Recording() {
    }

    public String getArtist() {
        return artist;
    }

    public Recording setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public Recording setSongTitle(String songTitle) {
        this.songTitle = songTitle;
        return this;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public Recording setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
        return this;
    }

    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public Recording setYearOfIssue(Integer yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
        return this;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public Recording setCoverPath(String coverPath) {
        this.coverPath = coverPath;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public Recording setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public Recording setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public Recording setAudioPath(String audioPath) {
        this.audioPath = audioPath;
        return this;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public Recording setVideoPath(String videoPath) {
        this.videoPath = videoPath;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recording recording = (Recording) o;
        return Objects.equals(artist, recording.artist) &&
                Objects.equals(songTitle, recording.songTitle) &&
                Objects.equals(albumTitle, recording.albumTitle) &&
                Objects.equals(yearOfIssue, recording.yearOfIssue) &&
                Objects.equals(coverPath, recording.coverPath) &&
                Objects.equals(genre, recording.genre) &&
                Objects.equals(duration, recording.duration) &&
                Objects.equals(audioPath, recording.audioPath) &&
                Objects.equals(videoPath, recording.videoPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, songTitle, albumTitle, yearOfIssue, coverPath, genre, duration, audioPath, videoPath);
    }

}
