package com.rashm1n.twtr.followerservice.model.message;

public class UserFollowerMessageDTO {
    private String user_id;
    private String follower_id;
    private Action action;

    public enum Action {
        ADD, DELETE
    }

    public UserFollowerMessageDTO(String user_id, String follower_id, Action action) {
        this.user_id = user_id;
        this.follower_id = follower_id;
        this.action = action;
    }

    public UserFollowerMessageDTO() {
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

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
