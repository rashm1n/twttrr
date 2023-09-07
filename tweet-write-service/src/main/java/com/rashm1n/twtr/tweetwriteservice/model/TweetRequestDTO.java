package com.rashm1n.twtr.tweetwriteservice.model;

public class TweetRequestDTO {
    private String content;
    private String userId;
    private String parentTweetId;

    public TweetRequestDTO() {
    }

    public TweetRequestDTO(String content, String userId) {
        this.content = content;
        this.userId = userId;
    }

    public TweetRequestDTO(String content, String userId, String parentTweetId) {
        this.content = content;
        this.userId = userId;
        this.parentTweetId = parentTweetId;
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

    public String getParentTweetId() {
        return parentTweetId;
    }

    public void setParentTweetId(String parentTweetId) {
        this.parentTweetId = parentTweetId;
    }
}
