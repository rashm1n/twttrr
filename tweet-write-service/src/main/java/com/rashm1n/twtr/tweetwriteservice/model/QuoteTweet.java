package com.rashm1n.twtr.tweetwriteservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class QuoteTweet {
    @Id
    @Column(columnDefinition = "VARCHAR2(36)")
    private String quote_tweet_id;
    private String user_id;
    private String tweet_id;
    private String content;

    public QuoteTweet(String quote_tweet_id, String user_id, String tweet_id, String content) {
        this.user_id = user_id;
        this.tweet_id = tweet_id;
        this.quote_tweet_id = quote_tweet_id;
        this.content = content;
    }

    public QuoteTweet() {
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

    public String getQuote_tweet_id() {
        return quote_tweet_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTweet_id(String tweet_id) {
        this.tweet_id = tweet_id;
    }

    public void setQuote_tweet_id(String quote_tweet_id) {
        this.quote_tweet_id = quote_tweet_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
