package org.serhb.tweetfeed.service;

import org.junit.Test;
import org.springframework.social.twitter.api.Tweet;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Provides the junit tests to check capabilities of Twitter Service
 */
public class TwitterServiceTest {

    /**
     * Tests user timeline with the valid parameters
     */
    @Test
    public void testGetUserTimeLine() {
        TwitterService testService = new TwitterService();

        String validConsumerKey = "T2mcl0ZfdSLVocQPGK37OVhWG";
        String validConsumerSecret = "3CdqWh66XImdbpTSzYSKqFPHoFW2MvMOYeQY6HHiC1JVBL07De";
        String validUserScreenName = "SBavykin";
        Integer count = 20;

        List<Tweet> data = testService.getUserTimeLine(validConsumerKey, validConsumerSecret, validUserScreenName, count);

        assertNotNull(data);
        assertTrue(data.size() <= count);
        assertFalse(data.size() == 0);
    }

}
