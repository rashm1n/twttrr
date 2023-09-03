package com.rashm1n.twtr.tweetwriteservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Tweet {
    @Id
    @Column(columnDefinition = "VARCHAR2(36)")
    private String uuid;
    private String content;
    private String userId;
    private Instant createdAt;
    private int retweetCount;
    private int likesCount;

    public Tweet() {
    }

    public Tweet(String uuid, String content, String userId, Instant createdAt, int retweetCount, int likesCount) {
        this.uuid = uuid;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.retweetCount = retweetCount;
        this.likesCount = likesCount;
    }

    public Tweet incrementRetweetCount() {
        this.retweetCount++;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Tweet decrementRetweetCount() {
        this.retweetCount--;
        return this;
    }

    public Tweet incrementLikesCount() {
        this.likesCount++;
        return this;
    }

    public Tweet decrementLikesCount() {
        this.likesCount--;
        return this;
    }
}
