package com.rasm1n.twtr.timelinemixerservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

public class TweetDTO {
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("content")
    private String content;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("created_at")
    private Instant createdAt;


    public TweetDTO() {
    }

    public TweetDTO(UUID uuid, String content, String userId, Instant createdAt) {
        this.uuid = uuid;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
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
}
