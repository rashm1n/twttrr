package com.rashm1n.twtr.likeservice.model;

import java.time.Instant;

public class Like {
    private String userId;
    private String tweetId;
    private Instant createdAt;
    private boolean isRetweet;

    public Like() {
    }

    public Like(String userId, String tweetId, Instant createdAt, boolean isRetweet) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.createdAt = createdAt;
        this.isRetweet = isRetweet;
    }

    public Like(String userId, String tweetId) {
        this.userId = userId;
        this.tweetId = tweetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }
}
