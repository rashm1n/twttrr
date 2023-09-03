package com.rashm1n.twtr.likeservice.model;

public class LikeDTO {
    private String userId;
    private String tweetId;

    public LikeDTO() {
    }

    public LikeDTO(String userId, String tweetId) {
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
}
