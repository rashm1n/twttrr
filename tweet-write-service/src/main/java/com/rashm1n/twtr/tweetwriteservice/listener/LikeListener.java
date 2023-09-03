package com.rashm1n.twtr.tweetwriteservice.listener;

import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import com.rashm1n.twtr.tweetwriteservice.model.message.LikeMessageDTO;
import com.rashm1n.twtr.tweetwriteservice.service.TweetService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

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
        if (likeMessageDTO.getAction()==LikeMessageDTO.Action.LIKED) {
            Tweet tweet = tweetService.likeTweet(likeMessageDTO);
            if (tweet != null) {
                acknowledgment.acknowledge();
            }
        }
        else if (likeMessageDTO.getAction()== LikeMessageDTO.Action.UNLIKED) {
            Tweet tweet = tweetService.unlikeTweet(likeMessageDTO);
            if (tweet != null) {
                acknowledgment.acknowledge();
            }
        }
    }
}
