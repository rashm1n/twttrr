package com.rashm1n.twtr.followerservice.service;

import com.rashm1n.twtr.followerservice.Dao.FollowerDao;
import com.rashm1n.twtr.followerservice.model.FollowerDTO;
import com.rashm1n.twtr.followerservice.model.UserFollowersDTO;
import org.springframework.stereotype.Service;

@Service
public class FollowerService {
    private final FollowerDao followerDao;

    public FollowerService(FollowerDao followerDao) {
        this.followerDao = followerDao;
    }

    public FollowerDTO insertFollower(FollowerDTO followerDTO) {
       Long l = followerDao.addFollower(createKey(followerDTO.getUser_id()), followerDTO.getFollower_id());
       if (l> 0) {
           return followerDTO;
       }
       return null;
    }
    
    public boolean deleteFollower(String user_id, String follower_id) {
        Long l = followerDao.deleteFollower(createKey(user_id), follower_id);
        return l > 0;
    }

    public UserFollowersDTO getFollowers(String userId) {
        return new UserFollowersDTO(userId, followerDao.getFollowers(createKey(userId)));
    }

    private String createKey(String user_id) {
        return "followers:"+user_id;
    }
}
