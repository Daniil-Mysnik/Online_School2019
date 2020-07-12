package net.thumbtack.school.springBootRest.endpoint;

import net.thumbtack.school.springBootRest.dto.request.RecordingRequest;
import net.thumbtack.school.springBootRest.exceptions.RecordingException;
import net.thumbtack.school.springBootRest.model.Recording;
import net.thumbtack.school.springBootRest.service.PromotionServiceImpl;
import net.thumbtack.school.springBootRest.service.PublishingChannelsService;
import net.thumbtack.school.springBootRest.service.RecordingDataHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/recordings")
public class RecordingEndpoint {
    private final PublishingChannelsService publishingChannelsService;
    private final PromotionServiceImpl promotionService;
    private final RecordingDataHub recordingDataHub;

    @Autowired
    public RecordingEndpoint(PublishingChannelsService publishingChannelsService, PromotionServiceImpl promotionService, RecordingDataHub recordingDataHub) {
        this.publishingChannelsService = publishingChannelsService;
        this.promotionService = promotionService;
        this.recordingDataHub = recordingDataHub;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Recording save(@Valid @RequestBody RecordingRequest recordingRequest) throws RecordingException {
        return recordingDataHub.save(recordingRequest);
    }

    @PostMapping(value = "{path}/publish")
    public String publish(@PathVariable(value = "path") String path) throws RecordingException {
        Recording recording = recordingDataHub.getByPath(path);
        return publishingChannelsService.publish(recording, ZonedDateTime.now());
    }

    @DeleteMapping(value = "{path}")
    public String deleteRecording(@PathVariable(value = "path") String path) throws RecordingException {
        return recordingDataHub.delete(path);
    }

    @PostMapping(value = "{songTitle}")
    public String createCampaign(@PathVariable(value = "songTitle") String songTitle) throws RecordingException {
        return promotionService.createCampaign(songTitle, ZonedDateTime.now());
    }

    @PatchMapping(value = "{songTitle}")
    public String stopCampaign(@PathVariable(value = "songTitle") String songTitle) throws RecordingException {
        return promotionService.stopCampaign(songTitle);
    }

    @DeleteMapping(value = "campaign/{songTitle}")
    public String deleteCompany(@PathVariable(value = "songTitle") String songTitle) throws RecordingException {
        return promotionService.deleteCampaign(songTitle);
    }

}
