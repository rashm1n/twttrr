package com.rashm1n.twtr.tweetwriteservice.service;

import com.rashm1n.twtr.tweetwriteservice.exception.TweetNotFoundException;
import com.rashm1n.twtr.tweetwriteservice.model.QuoteTweet;
import com.rashm1n.twtr.tweetwriteservice.model.QuoteTweetDTO;
import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import com.rashm1n.twtr.tweetwriteservice.model.message.QuoteMessageDTO;
import com.rashm1n.twtr.tweetwriteservice.repository.QuoteTweetRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuoteTweetService {
    public QuoteTweetRepository quoteTweetRepository;
    private final KafkaTemplate kafkaTemplate;
    TweetService tweetService;

    public QuoteTweetService(QuoteTweetRepository quoteTweetRepository,
            @Qualifier("quoteTweetKafkaTemplate") KafkaTemplate<String, QuoteMessageDTO> kafkaTemplate,
            TweetService tweetService) {
        this.quoteTweetRepository = quoteTweetRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.tweetService = tweetService;
    }

    public QuoteTweet createQuoteTweet(QuoteTweetDTO quoteTweetDTO) {
        Optional<Tweet> tweet = tweetService.getTweetOptionalById(quoteTweetDTO.getTweet_id());
        if (!tweet.isPresent()) {
            throw new TweetNotFoundException();
        } else {
            QuoteTweet quoteTweet = new QuoteTweet(
                    UUID.randomUUID().toString(),
                    quoteTweetDTO.getUser_id(),
                    quoteTweetDTO.getTweet_id(),
                    quoteTweetDTO.getContent());
            kafkaTemplate.send("quoteTweet", quoteTweet.getUser_id(), new QuoteMessageDTO(
                    quoteTweet.getQuote_tweet_id(), quoteTweet.getUser_id(), quoteTweet.getTweet_id(),
                    QuoteMessageDTO.Action.QUOTED
            ));
            return quoteTweetRepository.save(quoteTweet);
        }
    }

    public Void deleteQuoteTweet(String quoteTweetId) {
        Optional<QuoteTweet> quoteTweet = quoteTweetRepository.findById(quoteTweetId);
        if (quoteTweet.isPresent()) {
            quoteTweetRepository.deleteById(quoteTweetId);
            kafkaTemplate.send("quoteTweet", quoteTweet.get().getUser_id(), new QuoteMessageDTO(
                    quoteTweet.get().getQuote_tweet_id(), quoteTweet.get().getUser_id(), quoteTweet.get().getTweet_id(),
                    QuoteMessageDTO.Action.UNQUOTED
            ));
            return null;
        } else {
            throw new TweetNotFoundException();
        }
    }
}
