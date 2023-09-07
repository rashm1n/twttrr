package com.rashm1n.twtr.tweetwriteservice.controller;

import com.rashm1n.twtr.tweetwriteservice.exception.TweetNotFoundException;
import com.rashm1n.twtr.tweetwriteservice.model.RetweetDTO;
import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import com.rashm1n.twtr.tweetwriteservice.service.RetweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/retweets")
public class RetweetController {
    public RetweetService retweetService;

    public RetweetController(RetweetService retweetService) {
        this.retweetService = retweetService;
    }

    @PostMapping
    public ResponseEntity<Tweet> createRetweet(@RequestBody RetweetDTO retweetDTO) {
        return ResponseEntity.ok(retweetService.createRetweet(retweetDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteRetweet(@PathVariable String uuid) {
        try {
            retweetService.deleteRetweet(uuid);
        } catch (TweetNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }
}
