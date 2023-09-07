package com.rashm1n.twtr.tweetwriteservice.service;

import com.rashm1n.twtr.tweetwriteservice.exception.TweetNotFoundException;
import com.rashm1n.twtr.tweetwriteservice.model.RetweetDTO;
import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RetweetService {
    TweetService tweetService;

    public RetweetService(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    public Tweet createRetweet(RetweetDTO retweetDTO) {
        Optional<Tweet> tweet = tweetService.getTweetOptionalById(retweetDTO.getTweetUuid());
        if (!tweet.isPresent()) {
            throw new TweetNotFoundException();
        } else {
            Tweet retweet = Tweet.builder()
                    .uuid(UUID.randomUUID().toString())
                    .userId(retweetDTO.getUserId())
                    .referenceTweetId(retweetDTO.getTweetUuid())
                    .build();

            Tweet savedTweet = tweetService.createTweet(retweet);
            tweetService.updateTweet(tweet.get().incrementRetweetCount());
            return savedTweet;
        }
    }

    public void deleteRetweet(String uuid) {
        Optional<Tweet> tweet = tweetService.getTweetOptionalById(uuid);
        if (tweet.isPresent()) {
            tweetService.deleteTweet(uuid);
            tweetService.updateTweet(tweet.get().decrementRetweetCount());
        } else {
            throw new TweetNotFoundException();
        }
    }
}
