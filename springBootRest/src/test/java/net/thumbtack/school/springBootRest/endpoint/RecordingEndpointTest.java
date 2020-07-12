package net.thumbtack.school.springBootRest.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.school.springBootRest.dto.request.RecordingRequest;
import net.thumbtack.school.springBootRest.exceptions.GlobalExceptionHandler;
import net.thumbtack.school.springBootRest.model.Recording;
import net.thumbtack.school.springBootRest.service.PromotionServiceImpl;
import net.thumbtack.school.springBootRest.service.PublishingChannelsService;
import net.thumbtack.school.springBootRest.service.RecordingDataHub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RecordingEndpoint.class)
public class RecordingEndpointTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PublishingChannelsService publishingChannelsService;

    @MockBean
    private PromotionServiceImpl promotionService;

    @MockBean
    private RecordingDataHub recordingDataHub;

    @Test
    public void testSave() throws Exception {
        RecordingRequest recordingRequest = new RecordingRequest();

        recordingRequest.setArtist("Lil Peep");
        recordingRequest.setSongTitle("'Save this shit'");
        recordingRequest.setAlbumTitle("Come Over When You're Sober");
        recordingRequest.setYearOfIssue(1968);
        recordingRequest.setCoverPath("https://laisdfhasi.com");
        recordingRequest.setGenre("HipHop-Rap");
        recordingRequest.setDuration("3:52");
        recordingRequest.setAudioPath("SomeAudioPath");
        recordingRequest.setVideoPath("SomeVideoPath");

        MvcResult mvcResult = mvc.perform(post("/api/recordings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recordingRequest)))
                .andReturn();
        assertEquals(mvcResult.getResponse().getStatus(), 400);
        GlobalExceptionHandler.MyError exception = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), GlobalExceptionHandler.MyError.class);
        assertEquals(1, exception.getAllErrors().size());
        assertEquals("Min value: 1970", exception.getAllErrors().get(0));
    }

}
