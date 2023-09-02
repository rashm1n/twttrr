package com.rashm1n.twtr.tweetwriteservice.controller;

import com.rashm1n.twtr.tweetwriteservice.exception.TweetNotFoundException;
import com.rashm1n.twtr.tweetwriteservice.model.Retweet;
import com.rashm1n.twtr.tweetwriteservice.model.RetweetDTO;
import com.rashm1n.twtr.tweetwriteservice.service.RetweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweets")
public class RetweetController {
    public RetweetService retweetService;

    public RetweetController(RetweetService retweetService) {
        this.retweetService = retweetService;
    }

    @PostMapping
    public ResponseEntity<Retweet> createRetweet(@RequestBody RetweetDTO retweetDTO) {
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
