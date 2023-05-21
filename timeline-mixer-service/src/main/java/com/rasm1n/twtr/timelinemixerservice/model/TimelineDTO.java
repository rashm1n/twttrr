package com.rasm1n.twtr.timelinemixerservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class TimelineDTO implements Serializable {
    @JsonProperty("user_id")
    private String user_id;
    @JsonProperty("tweet_ids")
    private List<String> tweet_ids;

    public TimelineDTO(String user_id, List<String> tweet_ids) {
        this.user_id = user_id;
        this.tweet_ids = tweet_ids;
    }

    public TimelineDTO() {
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public List<String> getTimelineEntries() {
        return tweet_ids;
    }

    public void setTimelineEntries(List<String> tweet_ids) {
        this.tweet_ids = tweet_ids;
    }
}
