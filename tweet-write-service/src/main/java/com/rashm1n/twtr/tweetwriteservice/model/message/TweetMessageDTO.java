package com.rashm1n.twtr.tweetwriteservice.model.message;

import java.time.Instant;

public class TweetMessageDTO {
    private String user_id;
    private String tweet_id;
    private Instant timestamp;

    private Action action;

    public TweetMessageDTO(String user_id, String tweet_id, Instant timestamp, Action action) {
        this.user_id = user_id;
        this.tweet_id = tweet_id;
        this.timestamp = timestamp;
        this.action = action;
    }

    public TweetMessageDTO() {
    }

    public enum Action {
        CREATED, UPDATED, DELETED
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(String tweet_id) {
        this.tweet_id = tweet_id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
