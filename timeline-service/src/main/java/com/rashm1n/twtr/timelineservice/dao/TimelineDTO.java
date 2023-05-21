package com.rashm1n.twtr.timelineservice.dao;

import java.util.LinkedHashSet;

public class TimelineDTO {
    private String user_id;
    private LinkedHashSet<String> tweet_ids;

    public TimelineDTO(String user_id, LinkedHashSet<String> tweet_ids) {
        this.user_id = user_id;
        this.tweet_ids = tweet_ids;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public LinkedHashSet<String> getTweet_ids() {
        return tweet_ids;
    }

    public void setTweet_ids(LinkedHashSet<String> tweet_ids) {
        this.tweet_ids = tweet_ids;
    }
}
