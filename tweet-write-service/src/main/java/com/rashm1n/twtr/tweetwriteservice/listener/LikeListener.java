package com.rashm1n.twtr.tweetwriteservice.listener;

import com.rashm1n.twtr.tweetwriteservice.exception.TweetNotFoundException;
import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import com.rashm1n.twtr.tweetwriteservice.model.message.LikeMessageDTO;
import com.rashm1n.twtr.tweetwriteservice.service.TweetService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LikeListener {
    @Value("${tweet-write-svc.kafka.client-id}")
    public String client_id;

    public TweetService tweetService;

    public LikeListener(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @KafkaListener(topics = "like", groupId = "tweet-write-service")
    public void listen(LikeMessageDTO likeMessageDTO, Acknowledgment acknowledgment) {
        String tweetId;
        Tweet updatedTweet;
        if (likeMessageDTO.isRetweet()) {
            Optional<Tweet> tweetOptional = tweetService.getTweetOptionalById(likeMessageDTO.getTweetId());
            if (tweetOptional.isPresent()) {
                tweetId = tweetOptional.get().getReferenceTweetId();
            } else {
                throw new TweetNotFoundException();
            }
        } else {
            tweetId = likeMessageDTO.getTweetId();
        }

        if (likeMessageDTO.getAction()==LikeMessageDTO.Action.LIKED) {
            updatedTweet = tweetService.likeOrUnlikeTweet(tweetId, true);
        }
        else if (likeMessageDTO.getAction()== LikeMessageDTO.Action.UNLIKED) {
            updatedTweet = tweetService.likeOrUnlikeTweet(tweetId,false);
        } else {
            throw new RuntimeException();
        }

        if (updatedTweet!=null) {
            acknowledgment.acknowledge();
        } else {
            throw new RuntimeException();
        }
    }
}
