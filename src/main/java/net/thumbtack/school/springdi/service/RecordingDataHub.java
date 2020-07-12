package net.thumbtack.school.springdi.service;

import net.thumbtack.school.springdi.storage.DataStorage;
import net.thumbtack.school.springdi.model.Recording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RecordingDataHub {
    private final DataStorage audioRecordingStorage;
    private final DataStorage videoRecordingStorage;

    @Autowired
    public RecordingDataHub(@Qualifier("audioRecordingStorage") DataStorage audioRecordingStorage,
                            @Qualifier("videoRecordingStorage") DataStorage videoRecordingStorage) {
        this.audioRecordingStorage = audioRecordingStorage;
        this.videoRecordingStorage = videoRecordingStorage;
    }


    public String save(Recording recording) {
        if (recording == null || recording.getAudioPath() == null || recording.getVideoPath() == null) {
            return null;
        }
        recording.setAudioPath(audioRecordingStorage.save(recording.getAudioPath()));
        recording.setVideoPath(videoRecordingStorage.save(recording.getVideoPath()));
        return "Audio saved by path: " + recording.getAudioPath() +
                " video saved by path: " + recording.getVideoPath();
    }

}
