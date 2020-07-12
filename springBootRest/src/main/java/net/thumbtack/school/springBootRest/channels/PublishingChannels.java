package net.thumbtack.school.springBootRest.channels;

import net.thumbtack.school.springBootRest.model.Recording;

import java.time.ZonedDateTime;

public interface PublishingChannels {
    String publish(Recording recording, ZonedDateTime publishAvailableDate);

}
