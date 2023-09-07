package com.rashm1n.twtr.tweetwriteservice.model;

import java.time.Instant;

public class TweetResponseDTO {
    private String id;
    private String content;
    private String userId;
    private Instant createdAt;
    private int retweetCount;
    private int likesCount;
    private String parentTweetId;
    private int repliesCount;

    public TweetResponseDTO() {
    }

    public TweetResponseDTO(String id, String content, String userId, Instant createdAt, int retweetCount, int likesCount,
            String parentTweetId, int repliesCount) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.retweetCount = retweetCount;
        this.likesCount = likesCount;
        this.parentTweetId = parentTweetId;
        this.repliesCount = repliesCount;
    }

    public TweetResponseDTO(Tweet tweet, Long repliesCount) {
        this.id = tweet.getUuid();
        this.content = tweet.getContent();
        this.userId = tweet.getUserId();
        this.createdAt = tweet.getCreatedAt();
        this.retweetCount = tweet.getRetweetCount();
        this.likesCount = tweet.getLikesCount();
        this.parentTweetId = tweet.getParentTweetId();
        this.repliesCount = repliesCount.intValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getParentTweetId() {
        return parentTweetId;
    }

    public void setParentTweetId(String parentTweetId) {
        this.parentTweetId = parentTweetId;
    }

    public int getRepliesCount() {
        return repliesCount;
    }

    public void setRepliesCount(int repliesCount) {
        this.repliesCount = repliesCount;
    }
}
