package com.rashm1n.twtr.tweetwriteservice.repository;

import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, String> {
    List<Tweet> findAllByUserId(String userId);
    List<Tweet> findAllByUserIdOrderByCreatedAtDesc(String userId);
}
