package com.rashm1n.twtr.tweetwriteservice.model;

public class RetweetDTO {
    private String userId;
    private String tweetUuid;

    public RetweetDTO() {
    }

    public RetweetDTO(String userId, String tweetUuid) {
        this.userId = userId;
        this.tweetUuid = tweetUuid;
    }

    public String getUserId() {
        return userId;
    }

    public String getTweetUuid() {
        return tweetUuid;
    }
}
