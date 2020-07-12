package net.thumbtack.school.springdi.service;

import net.thumbtack.school.springdi.model.Recording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PromotionServiceImpl implements PromotionService {
    private final PublishingChannelsService publishingChannelsService;
    private final RecordingDataHub recordingDataHub;

    @Autowired
    public PromotionServiceImpl(PublishingChannelsService publishingChannelsService, RecordingDataHub recordingDataHub) {
        this.publishingChannelsService = publishingChannelsService;
        this.recordingDataHub = recordingDataHub;
    }

    public void createCampaign(Recording recording, ZonedDateTime campaignCreateDate) {
        System.out.println("Created campaign for song " + recording.getSongTitle() + " in time: " + campaignCreateDate);
        recordingDataHub.save(recording);
        publishingChannelsService.publish(recording, ZonedDateTime.now());
    }

}
