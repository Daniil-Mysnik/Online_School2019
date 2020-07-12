package net.thumbtack.school.springdi;

import net.thumbtack.school.springdi.model.Recording;
import net.thumbtack.school.springdi.service.PromotionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class CmdOperations implements CommandLineRunner {

    private final PromotionServiceImpl promotionServiceImpl;

    @Autowired
    public CmdOperations(PromotionServiceImpl promotionServiceImpl) {
        this.promotionServiceImpl = promotionServiceImpl;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started");
        Recording recording = new Recording("Lil Peep", "'Save this shit'", "Come Over When You're Sober",
                "2017", "https://laisdfhasi.com", "HipHop-Rap",
                "3:52", "SomeAudioPath", "SomeVideoPath");

        promotionServiceImpl.createCampaign(recording, ZonedDateTime.of(LocalDateTime.from(ZonedDateTime.now()), ZoneId.of("Europe/Moscow")));
        System.out.println("Stopped");
    }
}
