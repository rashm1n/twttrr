package com.rashm1n.twtr.timelineservice.dto;

public class TimelineEntryDTO {
    private String tweet_id;
    private String user_id;
    private String timestamp;

    public TimelineEntryDTO(String tweet_id, String user_id, String timestamp) {
        this.tweet_id = tweet_id;
        this.user_id = user_id;
        this.timestamp = timestamp;
    }

    public TimelineEntryDTO() {
    }

    public String getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(String tweet_id) {
        this.tweet_id = tweet_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override public String toString() {
        return "TimelineEntryDTO{" + "tweet_id='" + tweet_id + '\'' + ", user_id='" + user_id + '\'' + ", timestamp='"
                + timestamp + '\'' + '}';
    }
}
