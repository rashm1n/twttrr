package com.rashm1n.twtr.fanoutservice.service;

import com.rashm1n.twtr.fanoutservice.model.FanOutMessageDTO;
import com.rashm1n.twtr.fanoutservice.model.TweetMessageDTO;
import com.rashm1n.twtr.fanoutservice.model.UserFollowersDTO;
import com.rashm1n.twtr.fanoutservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashSet;

@Service
public class FanOutService {
    @Autowired
    KafkaTemplate kafkaTemplate;
    private LinkedHashSet<String> getFollowersForUID(String user_id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constants.FOLLOWER_SERVICE_URL + user_id;
        UserFollowersDTO response = restTemplate.getForObject(url, UserFollowersDTO.class);
        System.out.println("recieved followers for " + user_id);
        System.out.println("\n");
        for (String followerid : response.getFollower_ids()) {
            System.out.println(followerid);
        }
        return response.getFollower_ids();
    }

    public void fanOutTweet(TweetMessageDTO tweet) {
        String user_id = tweet.getUser_id();
        LinkedHashSet<String > followers = getFollowersForUID(user_id);
        for (String followerid : followers) {
            kafkaTemplate.send("twtrr-fanout", followerid, new FanOutMessageDTO(
                    followerid, tweet.getTweet_id(),
                    tweet.getTimestamp(),
                    FanOutMessageDTO.Action.CREATED
            ));
        }
    }
}
