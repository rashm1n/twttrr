package com.rashm1n.twtr.tweetwriteservice.service;

import com.rashm1n.twtr.tweetwriteservice.model.QuoteTweetDTO;
import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;

@Service
public class QuoteTweetService {
    TweetService tweetService;

    public QuoteTweetService(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    public Tweet createQuoteTweet(QuoteTweetDTO quoteTweetDTO) {
        Tweet quoteTweet = Tweet.builder()
                .uuid(UUID.randomUUID().toString())
                .content(quoteTweetDTO.getContent())
                .userId(quoteTweetDTO.getUser_id())
                .createdAt(Instant.now())
                .referenceTweetId(quoteTweetDTO.getTweet_id())
                .retweetCount(0)
                .likesCount(0)
                .build();

        return tweetService.createReferencedTweet(quoteTweet);

    }

    public Void deleteQuoteTweet(String quoteTweetId) {
        tweetService.deleteTweet(quoteTweetId);
        return null;
    }

}
