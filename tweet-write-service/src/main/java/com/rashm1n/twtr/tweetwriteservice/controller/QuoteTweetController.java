package com.rashm1n.twtr.tweetwriteservice.controller;

import com.rashm1n.twtr.tweetwriteservice.model.QuoteTweetDTO;
import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import com.rashm1n.twtr.tweetwriteservice.service.QuoteTweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/quote-tweets")
public class QuoteTweetController {
    public QuoteTweetService quoteTweetService;

    public QuoteTweetController(QuoteTweetService quoteTweetService) {
        this.quoteTweetService = quoteTweetService;
    }

    @PostMapping
    public ResponseEntity<Tweet> createQuoteTweet(@RequestBody QuoteTweetDTO quoteTweetDTO) {
        return ResponseEntity.ok(quoteTweetService.createQuoteTweet(quoteTweetDTO));
    }

    @DeleteMapping("/{quote_tweet_id}")
    public ResponseEntity<Void> deleteQuoteTweet(@PathVariable String quote_tweet_id) {
        return ResponseEntity.ok(quoteTweetService.deleteQuoteTweet(quote_tweet_id));
    }
}
