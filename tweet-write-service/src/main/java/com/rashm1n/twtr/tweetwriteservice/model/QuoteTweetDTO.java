package com.rashm1n.twtr.tweetwriteservice.model;

public class QuoteTweetDTO {
    private String user_id;
    private String tweet_id;
    private String content;

    public QuoteTweetDTO(String user_id, String tweet_id, String content) {
        this.user_id = user_id;
        this.tweet_id = tweet_id;
        this.content = content;
    }

    public QuoteTweetDTO() {
    }

    public enum Action {
        QUOTED, UNQUOTED
    }

    public String getUser_id() {
        return user_id;
    }

    public String getTweet_id() {
        return tweet_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTweet_id(String tweet_id) {
        this.tweet_id = tweet_id;
    }

    public String getContent() {
        return content;
    }
}
