package com.rashm1n.twtr.tweetwriteservice.repository;

import com.rashm1n.twtr.tweetwriteservice.model.Retweet;
import org.springframework.data.repository.CrudRepository;

public interface RetweetRepository extends CrudRepository<Retweet, String> {
}
