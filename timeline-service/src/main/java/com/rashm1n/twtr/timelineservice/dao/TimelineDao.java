package com.rashm1n.twtr.timelineservice.dao;

import com.rashm1n.twtr.timelineservice.model.Timeline;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

@Component
public class TimelineDao {
    final RedisTemplate<String, Object> redisTemplate;
    final StringRedisTemplate stringRedisTemplate;

    public TimelineDao(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public boolean insertTimelineEntry(String key, String value, Double score) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForZSet().add(key, value, score));
    }

    public boolean deleteTimelineEntry(String key, String value) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForZSet().remove(key, value));
    }

    public LinkedHashSet<String> getTimeline(String key) {
        Object a = stringRedisTemplate.opsForZSet().range(key, 0, -1);
        return (LinkedHashSet<String>) a;
    }
}
