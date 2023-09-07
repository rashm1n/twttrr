package com.rashm1n.twtr.tweetwriteservice.service;

import com.rashm1n.twtr.tweetwriteservice.exception.TweetNotFoundException;
import com.rashm1n.twtr.tweetwriteservice.model.TimelineDTO;
import com.rashm1n.twtr.tweetwriteservice.model.Tweet;
import com.rashm1n.twtr.tweetwriteservice.model.TweetRequestDTO;
import com.rashm1n.twtr.tweetwriteservice.model.TweetResponseDTO;
import com.rashm1n.twtr.tweetwriteservice.model.message.LikeMessageDTO;
import com.rashm1n.twtr.tweetwriteservice.model.message.TweetMessageDTO;
import com.rashm1n.twtr.tweetwriteservice.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TweetService {
    @Value("${tweet-write-svc.kafka.topic}")
    private String topic;
    private final TweetRepository tweetRepository;
    private final KafkaTemplate kafkaTemplate;

    public TweetService(TweetRepository tweetRepository, @Qualifier("tweetKafkaTemplate") KafkaTemplate<String, TweetMessageDTO> kafkaTemplate) {
        this.tweetRepository = tweetRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public TweetResponseDTO getTweetByUUID(String uuid) {
        Optional<Tweet> tweetOptional = tweetRepository.findById(uuid);
        if (tweetOptional.isPresent()) {
            Tweet tweet = tweetOptional.get();
            Long repliesCount = tweetRepository.countByParentTweetId(tweet.getUuid());
            return new TweetResponseDTO(tweet, repliesCount);
        }
        // TODO: Handle Exception
        return null;
    }

    public Tweet createTweet(TweetRequestDTO tweetRequestDTO) {
        Tweet tweet = tweetRepository.save(new Tweet(UUID.randomUUID().toString(), tweetRequestDTO.getContent(), tweetRequestDTO.getUserId(),
                Instant.now(), 0,0,tweetRequestDTO.getParentTweetId()));
        kafkaTemplate.send(topic, tweet.getUserId(), new TweetMessageDTO(
                tweet.getUserId(),
                tweet.getUuid().toString(),
                tweet.getCreatedAt(),
                TweetMessageDTO.Action.CREATED
                ));
        return tweet;
    }

    // not supported yet
    public Tweet updateTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public void deleteTweet(String uuid) throws TweetNotFoundException {
        Tweet fetchedTweet = null;
        Optional<Tweet> tweet = tweetRepository.findById(uuid);
        if (tweet.isPresent()) {
            fetchedTweet = tweet.get();
            tweetRepository.deleteById(uuid);
            kafkaTemplate.send(topic, fetchedTweet.getUserId(), new TweetMessageDTO(
                    fetchedTweet.getUserId(),
                    fetchedTweet.getUuid().toString(),
                    fetchedTweet.getCreatedAt(),
                    TweetMessageDTO.Action.DELETED
            ));
        } else {
            throw new TweetNotFoundException();
        }
    }

    public Optional<Tweet> getTweetOptionalById(String uuid) {
        return tweetRepository.findById(uuid);
    }

    public List<Tweet> getAllTweets() {
        return (List<Tweet>) tweetRepository.findAll();
    }

    public TimelineDTO getAllTweetsByUserId(String userId) {
        List<Tweet> tweets = tweetRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
        return new TimelineDTO(userId, tweets);
    }

    public Tweet likeTweet(LikeMessageDTO likeMessageDTO) {
        Optional<Tweet> tweet = tweetRepository.findById(likeMessageDTO.getTweetId());
        if (tweet.isPresent()) {
            Tweet fetchedTweet = tweet.get();
            fetchedTweet.incrementLikesCount();
            return tweetRepository.save(fetchedTweet);
        }
        return null;
    }

    public Tweet unlikeTweet(LikeMessageDTO likeMessageDTO) {
        Optional<Tweet> tweet = tweetRepository.findById(likeMessageDTO.getTweetId());
        if (tweet.isPresent()) {
            Tweet fetchedTweet = tweet.get();
            fetchedTweet.decrementLikesCount();
            return tweetRepository.save(fetchedTweet);
        }
        return null;
    }
}
