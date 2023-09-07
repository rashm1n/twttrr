package com.rashm1n.twtr.likeservice.service;

import com.rashm1n.twtr.likeservice.model.Like;
import com.rashm1n.twtr.likeservice.model.LikeDTO;
import com.rashm1n.twtr.likeservice.model.LikeMessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class LikeService {
    @Value("${like-svc.kafka.topic}")
    private String topic;
    KafkaTemplate likeKafkaTemplate;

    public LikeService(KafkaTemplate likeKafkaTemplate) {
        this.likeKafkaTemplate = likeKafkaTemplate;
    }

    public Like createLike(LikeDTO likeDTO) {
        Like like = new Like(likeDTO.getUserId(), likeDTO.getTweetId(), Instant.now(), likeDTO.isRetweet());
        LikeMessageDTO likeMessageDTO = new LikeMessageDTO(
                like.getUserId(),
                like.getTweetId(),
                LikeMessageDTO.Action.LIKED,
                like.getCreatedAt(),
                likeDTO.isRetweet()
        );
        likeKafkaTemplate.send(topic, like.getUserId(), likeMessageDTO);
        return like;
    }

    public void deleteLike(String tweetId, String userId, boolean isRetweet) {
        LikeMessageDTO likeMessageDTO = new LikeMessageDTO(
                userId,
                tweetId,
                LikeMessageDTO.Action.UNLIKED,
                Instant.now(),
                isRetweet
        );
        likeKafkaTemplate.send(topic, userId, likeMessageDTO);
    }
}
