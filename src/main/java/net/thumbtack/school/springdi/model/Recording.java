package net.thumbtack.school.springdi.model;

public class Recording {

    private String artist;
    private String songTitle;
    private String albumTitle;
    private String yearOfIssue;
    private String coverPath;
    private String genre;
    private String duration;
    private String audioPath;
    private String videoPath;

    public Recording(String artist, String songTitle, String albumTitle, String yearOfIssue, String coverPath, String genre, String duration, String audioPath, String videoPath) {
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

    public String getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(String yearOfIssue) {
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
