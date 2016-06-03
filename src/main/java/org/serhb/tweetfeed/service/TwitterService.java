package org.serhb.tweetfeed.service;

import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Encapsulates methods to access the twitter api
 * and represent a competent object like delegate which injects to controllers
 */
@Service
public class TwitterService {

    private Twitter twitterAPIFacade;

    /**
     * Retrieves user's timeline consists of the list with tweets information
     *
     * @param consumerKey           - an application key for retrieving the client token
     * @param consumerSecret        - an application secret key for retrieving the client token
     * @param twitterUserScreenName - an user's screen name in social
     * @param count                 - an amount of tweets per request
     * @return the list with tweets from specify user or nothing if an error presents
     */
    public List<Tweet> getUserTimeLine(String consumerKey, String consumerSecret, String twitterUserScreenName, int count) {
        sendConsumerCredentials(consumerKey, consumerSecret);
        TimelineOperations timeLineOps = twitterAPIFacade.timelineOperations();

        return timeLineOps.getUserTimeline(combineScreenName(twitterUserScreenName), count);
    }

    /**
     * Exchanges credentials of the application with client token then initializes the Twitter API Facade
     *
     * @param consumerKey    - an application key for retrieving the client token
     * @param consumerSecret - an application secret key for retrieving the client token
     */
    private void sendConsumerCredentials(String consumerKey, String consumerSecret) {
        twitterAPIFacade = new TwitterTemplate(consumerKey, consumerSecret);
    }

    /**
     * Adds '@' to screen name if this symbol not presents
     *
     * @param twitterScreenName - an user's screen name in social
     * @return combined screen name with '@' prefix
     */
    @org.jetbrains.annotations.Contract(pure = true)
    private String combineScreenName(String twitterScreenName) {
        return twitterScreenName.charAt(0) == '@' ? twitterScreenName : '@' + twitterScreenName;
    }
}
