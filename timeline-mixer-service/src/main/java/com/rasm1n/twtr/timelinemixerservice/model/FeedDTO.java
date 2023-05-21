package com.rasm1n.twtr.timelinemixerservice.model;

import java.util.LinkedHashSet;
import java.util.List;

public class FeedDTO {
    private String userId;
    private List<TweetDTO> tweets;
    public FeedDTO() {
    }
    public FeedDTO(String userId, List<TweetDTO> tweets) {
        this.userId = userId;
        this.tweets = tweets;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<TweetDTO> getTweets() {
        return tweets;
    }

    public void setTweets(List<TweetDTO> tweets) {
        this.tweets = tweets;
    }
}
