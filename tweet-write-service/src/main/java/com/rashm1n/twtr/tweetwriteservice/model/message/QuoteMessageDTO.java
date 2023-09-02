package com.rashm1n.twtr.tweetwriteservice.model.message;

public class QuoteMessageDTO {
    private String quoted_tweet_id;
    private String user_id;
    private String tweet_id;
    private Action action;
    public QuoteMessageDTO(String quoted_tweet_id, String user_id, String tweet_id, Action action) {
        this.user_id = user_id;
        this.tweet_id = tweet_id;
        this.quoted_tweet_id = quoted_tweet_id;
        this.action = action;
    }

    public enum Action {
        QUOTED, UNQUOTED
    }

    public QuoteMessageDTO() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(String tweet_id) {
        this.tweet_id = tweet_id;
    }

    public String getQuoted_tweet_id() {
        return quoted_tweet_id;
    }

    public void setQuoted_tweet_id(String quoted_tweet_id) {
        this.quoted_tweet_id = quoted_tweet_id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
