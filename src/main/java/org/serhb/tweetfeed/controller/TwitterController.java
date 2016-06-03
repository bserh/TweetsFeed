package org.serhb.tweetfeed.controller;

import org.serhb.tweetfeed.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Encapsulates a logic which handles the requests,
 * interacts with competent objects and delegates,
 * returns response depends on client needs
 * <p>
 * An endpoint to use these methods is /rest
 */
@RestController
@RequestMapping(value = "/rest")
public class TwitterController {
    @Autowired
    private TwitterService twitterService;

    /**
     * Retrieves tweets for specify user by him user's screen name on the Twitter service
     * There is a kind of request that doesn't support pagination. We can only specify an amount of tweets
     *
     * @param twitterUserScreenName - user's screen name in social
     * @param consumerKey           - an application key for retrieving the client token
     * @param consumerSecret        - an application secret key for retrieving the client token
     * @param count                 - an amount of tweets per request
     * @return the list of tweets as response entity with status 200, or 500 if an error from server occurred
     */
    @RequestMapping(value = "/timeline/{twitterUserScreenName}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    ResponseEntity<List<Tweet>> getUserTimeLine(
            @PathVariable String twitterUserScreenName,
            @RequestParam(value = "consumerKey") String consumerKey,
            @RequestParam(value = "consumerSecret") String consumerSecret,
            @RequestParam(value = "count") Integer count
    ) {
        List<Tweet> tweets = twitterService.getUserTimeLine(consumerKey, consumerSecret, twitterUserScreenName, count);

        return new ResponseEntity<List<Tweet>>(tweets, HttpStatus.OK);
    }
}
