package net.thumbtack.school.springBootRest;

import net.thumbtack.school.springBootRest.dto.request.RecordingRequest;
import net.thumbtack.school.springBootRest.model.Recording;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AcceptanceTest {
    private RestTemplate template = new RestTemplate();

    @Test
    public void testPostSong() {
        Recording recording = template.postForObject("http://localhost:8080/api/recordings", getRecordingRequest(), Recording.class);
        assertEquals("newSomeAudioPath", recording.getAudioPath());
        template.delete("http://localhost:8080/api/recordings/{path}", recording.getAudioPath());
    }

    @Test
    public void testPostCampaign() {
        template.postForObject("http://localhost:8080/api/recordings", getRecordingRequest(), Recording.class);
        String songTitle = "'Save this shit'";
        String response = template.postForObject("http://localhost:8080/api/recordings/{songTitle}", null, String.class, songTitle);
        assertNotNull(response);
        assertTrue(response.contains("Created campaign for song 'Save this shit'"));
        template.delete("http://localhost:8080/api/recordings/{path}", "newSomeAudioPath");
    }

    @Test
    public void testDeleteCampaign() {
        String songTitle = "'Save this shit'";
        template.postForObject("http://localhost:8080/api/recordings", getRecordingRequest(), Recording.class);
        template.delete("http://localhost:8080/api/recordings/{path}", "newSomeAudioPath");
        try {
            template.postForObject("http://localhost:8080/api/recordings/{songTitle}", null, String.class, songTitle);
        } catch (Exception ex) {
        }
    }

    private RecordingRequest getRecordingRequest() {
        RecordingRequest recordingRequest = new RecordingRequest();

        recordingRequest.setArtist("Lil Peep");
        recordingRequest.setSongTitle("'Save this shit'");
        recordingRequest.setAlbumTitle("Come Over When You're Sober");
        recordingRequest.setYearOfIssue(2017);
        recordingRequest.setCoverPath("https://laisdfhasi.com");
        recordingRequest.setGenre("HipHop-Rap");
        recordingRequest.setDuration("3:52");
        recordingRequest.setAudioPath("SomeAudioPath");
        recordingRequest.setVideoPath("SomeVideoPath");

        return recordingRequest;
    }

}
