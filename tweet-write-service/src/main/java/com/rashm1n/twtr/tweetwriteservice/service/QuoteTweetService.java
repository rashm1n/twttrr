package com.rashm1n.twtr.tweetwriteservice.service;

import com.rashm1n.twtr.tweetwriteservice.exception.TweetNotFoundException;
import com.rashm1n.twtr.tweetwriteservice.model.QuoteTweetDTO;
import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuoteTweetService {
    TweetService tweetService;

    public QuoteTweetService(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    public Tweet createQuoteTweet(QuoteTweetDTO quoteTweetDTO) {
        Optional<Tweet> tweet = tweetService.getTweetOptionalById(quoteTweetDTO.getTweet_id());
        if (!tweet.isPresent()) {
            throw new TweetNotFoundException();
        } else {
            Tweet quoteTweet = Tweet.builder()
                    .uuid(UUID.randomUUID().toString())
                    .content(quoteTweetDTO.getContent())
                    .userId(quoteTweetDTO.getUser_id())
                    .createdAt(Instant.now())
                    .referenceTweetId(quoteTweetDTO.getTweet_id())
                    .retweetCount(0)
                    .likesCount(0)
                    .build();

            Tweet savedTweet = tweetService.createTweet(quoteTweet);


            return savedTweet;
        }
    }

    public Void deleteQuoteTweet(String quoteTweetId) {
        tweetService.deleteTweet(quoteTweetId);
        return null;
    }

}
