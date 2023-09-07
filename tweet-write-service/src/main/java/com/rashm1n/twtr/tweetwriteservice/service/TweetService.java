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

        Tweet tweet = Tweet.builder()
                .uuid(UUID.randomUUID().toString())
                .content(tweetRequestDTO.getContent())
                .userId(tweetRequestDTO.getUserId())
                .createdAt(Instant.now())
                .parentTweetId(tweetRequestDTO.getParentTweetId())
                .build();

        Tweet savedTweet = tweetRepository.save(tweet);

        kafkaTemplate.send(topic, savedTweet.getUserId(), new TweetMessageDTO(
                savedTweet.getUserId(), savedTweet.getUuid(),
                savedTweet.getCreatedAt(),
                TweetMessageDTO.Action.CREATED
                ));
        return savedTweet;
    }

    public Tweet createTweet(Tweet tweet) {
        Tweet savedTweet = tweetRepository.save(tweet);
        sendTweetMessage(savedTweet, TweetMessageDTO.Action.CREATED);
        return savedTweet;
    }

    public Tweet createReferencedTweet(Tweet tweet) {
        Tweet fetchedTweet;
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweet.getReferenceTweetId());
        if (optionalTweet.isPresent()) {
            fetchedTweet = optionalTweet.get();
            tweetRepository.deleteById(tweet.getUuid());
            Tweet savedTweet = tweetRepository.save(fetchedTweet.incrementRetweetCount());
            sendTweetMessage(savedTweet, TweetMessageDTO.Action.CREATED);
            return savedTweet;
        } else {
            throw new TweetNotFoundException();
        }
    }

    public void deleteReferencedTweet(String uuid) {
        Tweet fetchedTweet;
        Optional<Tweet> tweet = tweetRepository.findById(uuid);
        if (tweet.isPresent()) {
            fetchedTweet = tweet.get();
            tweetRepository.deleteById(uuid);
            tweetRepository.save(fetchedTweet.decrementRetweetCount());
            sendTweetMessage(fetchedTweet, TweetMessageDTO.Action.DELETED);
        } else {
            throw new TweetNotFoundException();
        }
    }

    public void sendTweetMessage(Tweet tweet, TweetMessageDTO.Action action) {
        kafkaTemplate.send(topic, tweet.getUserId(), new TweetMessageDTO(
                tweet.getUserId(),
                tweet.getUuid(),
                tweet.getCreatedAt(),
                action
        ));
    }

    // not supported yet
    public Tweet updateTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public void deleteTweet(String uuid) throws TweetNotFoundException {
        Tweet fetchedTweet;
        Optional<Tweet> tweet = tweetRepository.findById(uuid);
        if (tweet.isPresent()) {
            fetchedTweet = tweet.get();
            tweetRepository.deleteById(uuid);
            sendTweetMessage(fetchedTweet, TweetMessageDTO.Action.DELETED);
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

    public Tweet likeOrUnlikeTweet(String tweetId, boolean like) {
        Optional<Tweet> tweet = tweetRepository.findById(tweetId);
        if (tweet.isPresent()) {
            Tweet fetchedTweet = tweet.get();
            if (like) {
                fetchedTweet.incrementLikesCount();
            } else {
                fetchedTweet.decrementLikesCount();
            }

            return tweetRepository.save(fetchedTweet);
        }
        return null;
    }
}
