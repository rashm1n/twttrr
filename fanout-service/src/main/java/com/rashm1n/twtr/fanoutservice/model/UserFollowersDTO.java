package com.rashm1n.twtr.fanoutservice.model;

import java.util.LinkedHashSet;

public class UserFollowersDTO {
    private String user_id;
    private LinkedHashSet<String> follower_ids;

    public UserFollowersDTO(String user_id, LinkedHashSet<String> follower_ids) {
        this.user_id = user_id;
        this.follower_ids = follower_ids;
    }

    public UserFollowersDTO() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public LinkedHashSet<String> getFollower_ids() {
        return follower_ids;
    }

    public void setFollower_ids(LinkedHashSet<String> follower_ids) {
        this.follower_ids = follower_ids;
    }

}
