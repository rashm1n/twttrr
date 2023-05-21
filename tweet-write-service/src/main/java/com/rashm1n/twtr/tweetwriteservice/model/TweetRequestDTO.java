package com.rashm1n.twtr.tweetwriteservice.model;

public class TweetRequestDTO {
    private String content;
    private String userId;

    public TweetRequestDTO() {
    }

    public TweetRequestDTO(String content, String userId) {
        this.content = content;
        this.userId = userId;
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
}
