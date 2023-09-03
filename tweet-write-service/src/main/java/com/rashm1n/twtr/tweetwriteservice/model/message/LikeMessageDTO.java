package com.rashm1n.twtr.tweetwriteservice.model.message;

import java.time.Instant;

public class LikeMessageDTO {
    private String userId;
    private String tweetId;
    private Action action;
    private Instant timestamp;

    public LikeMessageDTO(String userId, String tweetId, Action action, Instant timestamp) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.action = action;
        this.timestamp = timestamp;
    }

    public LikeMessageDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public String getTweetId() {
        return tweetId;
    }

    public Action getAction() {
        return action;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public enum Action {
        LIKED, UNLIKED
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
