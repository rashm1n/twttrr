package com.rashm1n.twtr.tweetwriteservice.controller;

import com.rashm1n.twtr.tweetwriteservice.model.TimelineDTO;
import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import com.rashm1n.twtr.tweetwriteservice.model.TweetRequestDTO;
import com.rashm1n.twtr.tweetwriteservice.model.TweetResponseDTO;
import com.rashm1n.twtr.tweetwriteservice.service.TweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TweetResponseDTO> getTweetByUUID(@PathVariable String uuid) {
        return ResponseEntity.ok(tweetService.getTweetByUUID(uuid));
    }

    @PostMapping
    public ResponseEntity<Tweet> createTweet(@RequestBody TweetRequestDTO tweetRequestDTO) {
        return ResponseEntity.ok(tweetService.createTweet(tweetRequestDTO));
    }

    @PutMapping
    public ResponseEntity<Tweet> updateTweet(@RequestBody Tweet tweet) {
        return ResponseEntity.ok(tweetService.updateTweet(tweet));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteTweet(@PathVariable String uuid) {
        try {
            tweetService.deleteTweet(uuid);
        } catch (com.rashm1n.twtr.tweetwriteservice.exception.TweetNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<TimelineDTO> getAllTweets(@RequestParam(required = false) String userId) {
        if (userId != null) {
            return ResponseEntity.ok(tweetService.getAllTweetsByUserId(userId));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
