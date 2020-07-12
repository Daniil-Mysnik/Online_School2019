package net.thumbtack.school.springBootRest.service;

import net.thumbtack.school.springBootRest.channels.PublishingChannels;
import net.thumbtack.school.springBootRest.exceptions.RecordingException;
import net.thumbtack.school.springBootRest.model.Recording;
import net.thumbtack.school.springBootRest.validator.RecordingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PublishingChannelsService {
    private final RecordingValidator recordingValidator;
    private final PublishingChannels itunes;
    private final PublishingChannels yandex;
    private final PublishingChannels youtube;

    @Autowired
    public PublishingChannelsService(RecordingValidator recordingValidator,
                                     @Qualifier("itunesMusicChannel") PublishingChannels itunes,
                                     @Qualifier("yandexMusicChannel") PublishingChannels yandex,
                                     @Qualifier("youtubeMusicChannel") PublishingChannels youtube) {
        this.recordingValidator = recordingValidator;
        this.itunes = itunes;
        this.yandex = yandex;
        this.youtube = youtube;
    }


    public String publish(Recording recording, ZonedDateTime publishAvailableDate) throws RecordingException {
        recordingValidator.checkCampaignByRecording(recording);
        recordingValidator.checkCampaignIsActive(recording);
        return itunes.publish(recording, publishAvailableDate) + "\n" +
                yandex.publish(recording, publishAvailableDate) + "\n" +
                youtube.publish(recording, publishAvailableDate);
    }

}
