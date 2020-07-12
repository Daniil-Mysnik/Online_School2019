package net.thumbtack.school.springBootRest.validator;

import net.thumbtack.school.springBootRest.DataBase;
import net.thumbtack.school.springBootRest.exceptions.RecordingErrorCode;
import net.thumbtack.school.springBootRest.exceptions.RecordingException;
import net.thumbtack.school.springBootRest.model.Campaign;
import net.thumbtack.school.springBootRest.model.Recording;
import org.springframework.stereotype.Service;

@Service
public class RecordingValidator {
    private final DataBase dataBase;

    public RecordingValidator(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void checkRecording(Recording recording) throws RecordingException {
        if (recording == null) {
            throw new RecordingException(RecordingErrorCode.WRONG_SONG);
        }
    }

    public void checkCampaignStarted(Campaign campaign) throws RecordingException {
        if (campaign == null) {
            throw new RecordingException(RecordingErrorCode.WRONG_CAMPAIGN);
        }
    }

    public void checkCampaignByRecording(Recording recording) throws RecordingException {
        if (dataBase.findCompanyByRecording(recording) == null) {
            throw new RecordingException(RecordingErrorCode.WRONG_SONG);
        }
    }

    public void checkCampaignIsActive(Recording recording) throws RecordingException {
        if (!dataBase.findCompanyByRecording(recording).isActive()) {
            throw new RecordingException(RecordingErrorCode.CAMPAIGN_STOPPED);
        }
    }

    public void checkDuplicateSong(Recording recording) throws RecordingException {
        if (dataBase.checkSongExists(recording)) {
            throw new RecordingException(RecordingErrorCode.DUPLICATE_SONG);
        }
    }

    public void checkSongExistByTitle(String songTitle) throws RecordingException {
        if (dataBase.getRecordingBySongTitle(songTitle) == null) {
            throw new RecordingException(RecordingErrorCode.WRONG_SONG);
        }
    }

    public void checkSongExistByPath(String path) throws RecordingException {
        if (dataBase.getRecordingByPath(path) == null) {
            throw new RecordingException(RecordingErrorCode.WRONG_SONG);
        }
    }

    public void checkCampaignExist(String songTitle) throws RecordingException {
        if (dataBase.findCompanyBySongTitle(songTitle) == null) {
            throw new RecordingException(RecordingErrorCode.CAMPAIGN_DOES_NOT_EXIST);
        }
    }

}
