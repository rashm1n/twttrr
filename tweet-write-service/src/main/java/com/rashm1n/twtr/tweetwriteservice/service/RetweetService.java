package com.rashm1n.twtr.tweetwriteservice.service;

import com.rashm1n.twtr.tweetwriteservice.exception.TweetNotFoundException;
import com.rashm1n.twtr.tweetwriteservice.model.RetweetDTO;
import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import com.rashm1n.twtr.tweetwriteservice.model.message.TweetMessageDTO;
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
        Tweet retweet = Tweet.builder()
                .uuid(UUID.randomUUID().toString())
                .userId(retweetDTO.getUserId())
                .referenceTweetId(retweetDTO.getTweetUuid())
                .build();
        return tweetService.createReferencedTweet(retweet);
    }

    public void deleteRetweet(String uuid) {
        tweetService.deleteReferencedTweet(uuid);
    }
}
