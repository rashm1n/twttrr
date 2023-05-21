package com.rashm1n.twtr.followerservice.Dao;

import com.rashm1n.twtr.followerservice.model.FollowerDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

@Component
public class FollowerDao {
    private final StringRedisTemplate stringRedisTemplate;

    public FollowerDao(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public Long addFollower(String key, String follower_id) {
        return stringRedisTemplate.opsForSet().add(key, follower_id);
    }

    public LinkedHashSet<String> getFollowers(String key) {
        return (LinkedHashSet<String>) stringRedisTemplate.opsForSet().members(key);
    }

    public Long deleteFollower(String key, String follower_id) {
        return stringRedisTemplate.opsForSet().remove(key, follower_id);
    }


}
