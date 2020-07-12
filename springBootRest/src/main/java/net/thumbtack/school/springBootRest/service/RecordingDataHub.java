package net.thumbtack.school.springBootRest.service;

import net.thumbtack.school.springBootRest.DataBase;
import net.thumbtack.school.springBootRest.converter.RecordingConverter;
import net.thumbtack.school.springBootRest.dto.request.RecordingRequest;
import net.thumbtack.school.springBootRest.exceptions.RecordingException;
import net.thumbtack.school.springBootRest.model.Recording;
import net.thumbtack.school.springBootRest.storage.DataStorage;
import net.thumbtack.school.springBootRest.validator.RecordingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RecordingDataHub {
    private final DataStorage audioRecordingStorage;
    private final DataStorage videoRecordingStorage;
    private final RecordingValidator recordingValidator;
    private final DataBase dataBase;

    @Autowired
    public RecordingDataHub(@Qualifier("audioRecordingStorage") DataStorage audioRecordingStorage,
                            @Qualifier("videoRecordingStorage") DataStorage videoRecordingStorage,
                            RecordingValidator recordingValidator,
                            DataBase dataBase) {
        this.audioRecordingStorage = audioRecordingStorage;
        this.videoRecordingStorage = videoRecordingStorage;
        this.recordingValidator = recordingValidator;
        this.dataBase = dataBase;
    }

    public Recording save(RecordingRequest recordingRequest) throws RecordingException {
        Recording recording = RecordingConverter.inflateEntity(recordingRequest);
        recordingValidator.checkDuplicateSong(recording);
        String audioPath = audioRecordingStorage.save(recordingRequest.getAudioPath());
        String videoPath = videoRecordingStorage.save(recordingRequest.getVideoPath());
        recording.setAudioPath(audioPath).setVideoPath(videoPath);
        dataBase.saveRecording(recording);
        return recording;
    }

    public Recording getByPath(String path) throws RecordingException {
        recordingValidator.checkSongExistByPath(path);
        Recording recording = dataBase.getRecordingByPath(path);
        recordingValidator.checkCampaignExist(recording.getSongTitle());
        return recording;
    }

    public String delete(String path) {
        dataBase.deleteRecording(path);
        return "Done";
    }

}
