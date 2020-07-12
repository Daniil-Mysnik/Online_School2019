package net.thumbtack.school.springBootRest;

import net.thumbtack.school.springBootRest.model.Campaign;
import net.thumbtack.school.springBootRest.model.Recording;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataBase {
    private List<Recording> recordings = new ArrayList<>();
    private List<Campaign> campaigns = new ArrayList<>();

    public void saveRecording(Recording recording) {
        recordings.add(recording);
    }

    public Recording getRecordingByPath(String path) {
        for (Recording rec : recordings) {
            if (rec.getAudioPath().equals(path)) {
                return rec;
            }
        }
        return null;
    }

    public boolean deleteRecording(String path) {
        return recordings.removeIf(rec -> rec.getAudioPath().equals(path));
    }

    public Recording getRecordingBySongTitle(String songTitle) {
        for (Recording rec : recordings) {
            if (rec.getSongTitle().equals(songTitle)) {
                return rec;
            }
        }
        return null;
    }

    public void createCampaign(Recording recording, ZonedDateTime zonedDateTime) {
        Campaign campaign = new Campaign(recording, zonedDateTime, true);
        campaigns.add(campaign);
    }

    public Campaign findCompanyByRecording(Recording recording) {
        for (Campaign comp : campaigns) {
            if (comp.getRecording().equals(recording)) {
                return comp;
            }
        }
        return null;
    }

    public Campaign findCompanyBySongTitle(String songTitle) {
        for (Campaign comp : campaigns) {
            if (comp.getRecording().getSongTitle().equals(songTitle)) {
                return comp;
            }
        }
        return null;
    }

    public boolean deleteCampaign(String songTitle) {
        return campaigns.removeIf(campaign -> campaign.getRecording().getSongTitle().equals(songTitle));
    }

    public boolean checkSongExists(Recording recording) {
        for (Recording rec : recordings) {
            if (rec.getSongTitle().equals(recording.getSongTitle())) {
                return true;
            }
        }
        return false;
    }

}
