package net.thumbtack.school.springBootRest.service;

import net.thumbtack.school.springBootRest.DataBase;
import net.thumbtack.school.springBootRest.exceptions.RecordingException;
import net.thumbtack.school.springBootRest.model.Campaign;
import net.thumbtack.school.springBootRest.model.Recording;
import net.thumbtack.school.springBootRest.validator.RecordingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PromotionServiceImpl implements PromotionService {
    private final RecordingValidator recordingValidator;
    private final DataBase dataBase;

    @Autowired
    public PromotionServiceImpl(RecordingValidator recordingValidator, DataBase dataBase) {
        this.recordingValidator = recordingValidator;
        this.dataBase = dataBase;
    }

    public String createCampaign(String songTittle, ZonedDateTime campaignCreateDate) throws RecordingException {
        Recording recording = dataBase.getRecordingBySongTitle(songTittle);
        recordingValidator.checkRecording(recording);
        dataBase.createCampaign(recording, campaignCreateDate);
        return "Created campaign for song " + songTittle + " in time: " + campaignCreateDate;
    }

    public String stopCampaign(String songTitle) throws RecordingException {
        Campaign campaign = dataBase.findCompanyBySongTitle(songTitle);
        recordingValidator.checkCampaignStarted(campaign);
        campaign.setActive(false);
        return "Campaign for song " + songTitle +" stopped";
    }

    public String deleteCampaign(String songTitle) throws RecordingException {
        recordingValidator.checkSongExistByTitle(songTitle);
        recordingValidator.checkCampaignExist(songTitle);
        return dataBase.deleteCampaign(songTitle) ? "Done" : "Error";
    }

}
