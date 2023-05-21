package com.rasm1n.twtr.timelinemixerservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasm1n.twtr.timelinemixerservice.model.FeedDTO;
import com.rasm1n.twtr.timelinemixerservice.model.TimelineDTO;
import com.rasm1n.twtr.timelinemixerservice.model.TweetDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    public FeedDTO getHomeFeed(String userId) {
        List<TweetDTO> tweets = null;
        try {
            TimelineDTO timelineDTO = getTimelineEntries(userId);
            tweets = new ArrayList<>();
            for (String tweetId : timelineDTO.getTimelineEntries()) {
                RestTemplate restTemplate = new RestTemplate();
//                TweetDTO tweetDTO = restTemplate.getForObject("http://localhost:8090/tweets/"+tweetId, TweetDTO.class);
                ResponseEntity<String> json = restTemplate.getForEntity("http://localhost:8090/tweets/"+tweetId,String.class);
                ObjectMapper mapper = new ObjectMapper();
                TweetDTO tweetDTO = mapper.readValue(json.getBody(), TweetDTO.class);
                tweets.add(tweetDTO);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new FeedDTO(userId, tweets);
    }

    public TimelineDTO getTimelineEntries(String userId) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
//        TimelineDTO timelineDTO = restTemplate.getForObject("http://localhost:9095/timeline/"+userId,TimelineDTO.class);
        ResponseEntity<String> json = restTemplate.getForEntity("http://localhost:9095/timeline/"+userId,String.class);

        ObjectMapper mapper = new ObjectMapper();
        TimelineDTO timelineDTO1 = mapper.readValue(json.getBody(), TimelineDTO.class);

        return timelineDTO1;
    }
}
