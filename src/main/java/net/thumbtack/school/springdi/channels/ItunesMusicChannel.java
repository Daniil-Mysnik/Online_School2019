package net.thumbtack.school.springdi.channels;

import net.thumbtack.school.springdi.model.Recording;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class ItunesMusicChannel implements PublishingChannels {

    @Override
    public void publish(Recording recording, ZonedDateTime publishAvailableDate) {
        System.out.println("Publishing audio "
                + "in Itunes, in time: "
                + publishAvailableDate.plusDays(7)
                + " by path: " + recording.getAudioPath());
    }

}
