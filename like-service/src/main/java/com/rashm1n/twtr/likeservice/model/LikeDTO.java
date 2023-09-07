package com.rashm1n.twtr.likeservice.model;

public class LikeDTO {
    private String userId;
    private String tweetId;
    private boolean isRetweet;

    public LikeDTO() {
    }

    public LikeDTO(String userId, String tweetId, boolean isRetweet) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.isRetweet = isRetweet;
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

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }
}
