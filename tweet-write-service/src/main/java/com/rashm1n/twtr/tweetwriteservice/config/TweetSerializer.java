package com.rashm1n.twtr.tweetwriteservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rashm1n.twtr.tweetwriteservice.model.message.TweetMessageDTO;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Map;

public class TweetSerializer implements Serializer<TweetMessageDTO> {
    private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, TweetMessageDTO tweetMessageDTO) {
        try {
            if (tweetMessageDTO == null) {
//                logger.info("Null received at serializing");
                return null;
            } else {
//                logger.info("Serializing . . .");
                return objectMapper.writeValueAsBytes(tweetMessageDTO);
            }
        } catch (JsonProcessingException e) {
//            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, TweetMessageDTO data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
