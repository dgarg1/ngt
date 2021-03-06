package com.hackathon.ngt;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.FilterStreamParameters;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class StreamService {

	private final Logger log = LoggerFactory.getLogger(StreamService.class);

    @Autowired
    private Twitter twitter;

    public Model streamApi(Model model, int time) throws InterruptedException{

        List<Tweet> tweets = new ArrayList<>();

        List<StreamListener> listeners = new ArrayList<StreamListener>();
        StreamListener streamListener = new StreamListener() {

            @Override
            public void onWarning(StreamWarningEvent warningEvent) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTweet(Tweet tweet) {
                System.out.println(tweet.getUser().getName() + " : " + tweet.getText());
                log.info("User '{}', Tweeted : {}", tweet.getUser().getName() , tweet.getText());
                tweets.add(tweet);
                model.addAttribute("tweets", tweets);
            }

            @Override
            public void onLimit(int numberOfLimitedTweets) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDelete(StreamDeleteEvent deleteEvent) {
                // TODO Auto-generated method stub

            }
        };

        listeners.add(streamListener);
        //This sets the GeoCode (-122.75,36.8,-121.75,37.8) of San Francisco(South-West and North-East) region as given in below twitter docs
        //https://dev.twitter.com/streaming/overview/request-parameters#locations
//        Float west=-122.75f;
//        Float south=36.8f;
//        Float east=-121.75f;
//        Float north = 37.8f;
//
//        FilterStreamParameters filterStreamParameters = new FilterStreamParameters();
//        //filterStreamParameters.addLocation(west, south, east, north);

        Stream userStream = twitter.streamingOperations().filter("#", listeners);
        Thread.sleep(Long.MAX_VALUE);
        userStream.close();
        return model;
    }
}
