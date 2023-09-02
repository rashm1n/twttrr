package com.rashm1n.twtr.tweetwriteservice.model.message;

public class RetweetMessageDTO {
    private String user_id;
    private String tweet_id;
    private String retweet_id;

    private Action action;

    public RetweetMessageDTO(String user_id, String tweet_id, String retweet_id, Action action) {
        this.user_id = user_id;
        this.tweet_id = tweet_id;
        this.retweet_id = retweet_id;
        this.action = action;
    }

    public RetweetMessageDTO() {
    }

    public enum Action {
        RETWEETED, UNRETWEETED
    }

    public String getUser_id() {
        return user_id;
    }

    public String getTweet_id() {
        return tweet_id;
    }

    public String getRetweet_id() {
        return retweet_id;
    }

    public Action getAction() {
        return action;
    }
}
