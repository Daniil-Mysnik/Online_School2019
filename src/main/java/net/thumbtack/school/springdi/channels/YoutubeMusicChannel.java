package net.thumbtack.school.springdi.channels;

import net.thumbtack.school.springdi.model.Recording;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class YoutubeMusicChannel implements PublishingChannels {
    @Override
    public void publish(Recording recording, ZonedDateTime publishAvailableDate) {
        System.out.println("Publishing audio "
                + "in YouTube, in time: "
                + publishAvailableDate.plusDays(7)
                + " by path: " + recording.getAudioPath());
        System.out.println("Publishing video "
                + "in YouTube, in time: "
                + publishAvailableDate.plusDays(14)
                + " by path: " + recording.getVideoPath());
    }

}
