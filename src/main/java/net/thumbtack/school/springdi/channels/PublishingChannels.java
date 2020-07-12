package net.thumbtack.school.springdi.channels;

import net.thumbtack.school.springdi.model.Recording;

import java.time.ZonedDateTime;

public interface PublishingChannels {

    void publish(Recording recording, ZonedDateTime publishAvailableDate);

}
