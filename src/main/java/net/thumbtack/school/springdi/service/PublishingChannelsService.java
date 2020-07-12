package net.thumbtack.school.springdi.service;

import net.thumbtack.school.springdi.channels.PublishingChannels;
import net.thumbtack.school.springdi.model.Recording;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PublishingChannelsService {

    private final PublishingChannels itunes;
    private final PublishingChannels yandex;
    private final PublishingChannels youtube;

    public PublishingChannelsService(@Qualifier("itunesMusicChannel") PublishingChannels itunes,
                                     @Qualifier("yandexMusicChannel") PublishingChannels yandex,
                                     @Qualifier("youtubeMusicChannel") PublishingChannels youtube) {
        this.itunes = itunes;
        this.yandex = yandex;
        this.youtube = youtube;
    }


    public void publish(Recording recording, ZonedDateTime publishAvailableDate) {
        itunes.publish(recording, publishAvailableDate);
        yandex.publish(recording, publishAvailableDate);
        youtube.publish(recording, publishAvailableDate);
    }

}
