package com.rashm1n.twtr.fanoutservice.listener;

import com.rashm1n.twtr.fanoutservice.model.FanOutMessageDTO;
import com.rashm1n.twtr.fanoutservice.model.TweetMessageDTO;
import com.rashm1n.twtr.fanoutservice.service.FanOutService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TweetListener {
    final FanOutService fanOutService;

    public TweetListener(FanOutService fanOutService) {
        this.fanOutService = fanOutService;
    }

    @KafkaListener(topics = "tweet", groupId = "fanout-service")
    public void listen(TweetMessageDTO tweetMessageDTO) {
        fanOutService.fanOutTweet(tweetMessageDTO);
    }
}
