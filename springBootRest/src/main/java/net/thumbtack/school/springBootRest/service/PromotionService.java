package net.thumbtack.school.springBootRest.service;

import net.thumbtack.school.springBootRest.exceptions.RecordingException;

import java.time.ZonedDateTime;

public interface PromotionService {
    String createCampaign(String songTitle, ZonedDateTime campaignCreateDate) throws RecordingException;

}
