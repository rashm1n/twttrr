package com.rasm1n.twtr.timelinemixerservice.service;

import com.rasm1n.twtr.timelinemixerservice.model.FeedDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserHomeService {
    public FeedDTO getUserFeed(String userId) {
        RestTemplate restTemplate = new RestTemplate();
        FeedDTO tweets = restTemplate.getForObject("http://localhost:8090/tweets?userId=" + userId, FeedDTO.class);
        return tweets;
    }
}
