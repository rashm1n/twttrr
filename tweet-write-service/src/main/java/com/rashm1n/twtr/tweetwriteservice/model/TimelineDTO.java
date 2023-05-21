package com.rashm1n.twtr.tweetwriteservice.model;

import java.util.List;

public class TimelineDTO {
    private String userId;
    private List<Tweet> tweets;

    public TimelineDTO(String userId, List<Tweet> tweets) {
        this.userId = userId;
        this.tweets = tweets;
    }

    public TimelineDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
