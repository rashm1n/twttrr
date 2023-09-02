package com.rashm1n.twtr.tweetwriteservice.service;

import com.rashm1n.twtr.tweetwriteservice.exception.TweetNotFoundException;
import com.rashm1n.twtr.tweetwriteservice.model.Retweet;
import com.rashm1n.twtr.tweetwriteservice.model.RetweetDTO;
import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import com.rashm1n.twtr.tweetwriteservice.model.message.RetweetMessageDTO;
import com.rashm1n.twtr.tweetwriteservice.repository.RetweetRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RetweetService {
    TweetService tweetService;
    RetweetRepository retweetRepository;
    private final KafkaTemplate kafkaTemplate;

    public RetweetService(TweetService tweetService,
            RetweetRepository retweetRepository,
            @Qualifier("retweetKafkaTemplate") KafkaTemplate<String, RetweetMessageDTO> kafkaTemplate) {
        this.tweetService = tweetService;
        this.retweetRepository = retweetRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Retweet createRetweet(RetweetDTO retweetDTO) {
        Optional<Tweet> tweet = tweetService.getTweetOptionalById(retweetDTO.getTweetUuid());
        if (!tweet.isPresent()) {
            throw new TweetNotFoundException();
        } else {
            Retweet retweetObj = retweetRepository.save(
                    new Retweet(
                            UUID.randomUUID().toString(),
                            retweetDTO.getUserId(),
                            retweetDTO.getTweetUuid()));
            tweetService.updateTweet(tweet.get().incrementRetweetCount());
            kafkaTemplate.send("retweet", retweetObj.getUserId(), new RetweetMessageDTO(
                        retweetObj.getUserId(), tweet.get().getUuid(), retweetObj.getUuid(),
                        RetweetMessageDTO.Action.RETWEETED
                        ));
            return retweetObj;
        }
    }

    public void deleteRetweet(String uuid) {
        Optional<Retweet> retweet = retweetRepository.findById(uuid);
        if (retweet.isPresent()) {
            retweetRepository.deleteById(uuid);
            Optional<Tweet> tweet = tweetService.getTweetOptionalById(retweet.get().getTweetUuid());
            if (tweet.isPresent()) {
                tweetService.updateTweet(tweet.get().decrementRetweetCount());
            }
            kafkaTemplate.send("retweet", retweet.get().getUserId(), new RetweetMessageDTO(
                        retweet.get().getUserId(), tweet.get().getUuid(), retweet.get().getUuid(),
                        RetweetMessageDTO.Action.UNRETWEETED
                        ));
        } else {
            throw new TweetNotFoundException();
        }
    }
}
