package com.rashm1n.twtr.tweetwriteservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Retweet {
    @Id
    @Column(columnDefinition = "VARCHAR2(36)")
    private String uuid;
    private String userId;
    private String tweetUuid;

    public Retweet() {
    }

    public Retweet(String uuid, String userId, String tweetUuid) {
        this.uuid = uuid;
        this.userId = userId;
        this.tweetUuid = tweetUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTweetUuid() {
        return tweetUuid;
    }

    public void setTweetUuid(String tweetUuid) {
        this.tweetUuid = tweetUuid;
    }
}
