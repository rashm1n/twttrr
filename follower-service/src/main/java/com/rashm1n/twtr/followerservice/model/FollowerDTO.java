package com.rashm1n.twtr.followerservice.model;

public class FollowerDTO {
    private String user_id;
    private String follower_id;

    public FollowerDTO(String user_id, String follower_id) {
        this.user_id = user_id;
        this.follower_id = follower_id;
    }

    public FollowerDTO() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(String follower_id) {
        this.follower_id = follower_id;
    }
}
