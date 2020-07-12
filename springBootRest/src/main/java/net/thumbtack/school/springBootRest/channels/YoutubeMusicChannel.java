package net.thumbtack.school.springBootRest.channels;

import net.thumbtack.school.springBootRest.model.Recording;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class YoutubeMusicChannel implements PublishingChannels {
    @Override
    public String publish(Recording recording, ZonedDateTime publishAvailableDate) {
        return  "Publishing audio "
                + "in YouTube, in time: "
                + publishAvailableDate.plusDays(7)
                + " by path: " + recording.getAudioPath() + "\n" +
                "Publishing video "
                + "in YouTube, in time: "
                + publishAvailableDate.plusDays(14)
                + " by path: " + recording.getVideoPath();
    }

}
