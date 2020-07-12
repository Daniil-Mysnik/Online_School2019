package net.thumbtack.school.springBootRest.converter;

import net.thumbtack.school.springBootRest.dto.request.RecordingRequest;
import net.thumbtack.school.springBootRest.model.Recording;

public class RecordingConverter {
    public static Recording inflateEntity(RecordingRequest recordingRequest) {
        return new Recording()
                .setArtist(recordingRequest.getArtist())
                .setSongTitle(recordingRequest.getSongTitle())
                .setAlbumTitle(recordingRequest.getAlbumTitle())
                .setYearOfIssue(recordingRequest.getYearOfIssue())
                .setCoverPath(recordingRequest.getCoverPath())
                .setGenre(recordingRequest.getGenre())
                .setDuration(recordingRequest.getDuration())
                .setAudioPath(recordingRequest.getAudioPath())
                .setVideoPath(recordingRequest.getVideoPath());
    }

}
