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
    private String referenceTweetId;
    private int retweetCount;
    private int likesCount;
    private String parentTweetId;

    public static TweetBuilder builder() {
        return new TweetBuilder();
    }

    public static class TweetBuilder {
        private String uuid;
        private String content;
        private String userId;
        private Instant createdAt;
        private String referenceTweetId;
        private int retweetCount;
        private int likesCount;
        private String parentTweetId;

        public TweetBuilder() {
            this.uuid = UUID.randomUUID().toString();
        }

        public TweetBuilder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public TweetBuilder content(String content) {
            this.content = content;
            return this;
        }

        public TweetBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public TweetBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TweetBuilder referenceTweetId(String referenceTweetId) {
            this.referenceTweetId = referenceTweetId;
            return this;
        }

        public TweetBuilder retweetCount(int retweetCount) {
            this.retweetCount = retweetCount;
            return this;
        }

        public TweetBuilder likesCount(int likesCount) {
            this.likesCount = likesCount;
            return this;
        }

        public TweetBuilder parentTweetId(String parentTweetId) {
            this.parentTweetId = parentTweetId;
            return this;
        }

        public Tweet build() {
            return new Tweet(uuid, content, userId, createdAt, referenceTweetId, retweetCount, likesCount, parentTweetId);
        }
    }

    protected Tweet() {
    }

    private Tweet(String uuid, String content, String userId, Instant createdAt, String referenceTweetId,
            int retweetCount, int likesCount, String parentTweetId) {
        this.uuid = uuid;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.referenceTweetId = referenceTweetId;
        this.retweetCount = retweetCount;
        this.likesCount = likesCount;
        this.parentTweetId = parentTweetId;
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

    public String getParentTweetId() {
        return parentTweetId;
    }

    public void setParentTweetId(String parentTweetId) {
        this.parentTweetId = parentTweetId;
    }

    public String getReferenceTweetId() {
        return referenceTweetId;
    }

    public void setReferenceTweetId(String referenceTweetId) {
        this.referenceTweetId = referenceTweetId;
    }
}
