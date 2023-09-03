package com.rashm1n.twtr.likeservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rashm1n.twtr.likeservice.model.LikeMessageDTO;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Map;

public class LikeSerializer implements Serializer<LikeMessageDTO> {
    private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

    @Override public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override public byte[] serialize(String s, LikeMessageDTO likeMessageDTO) {
        try {
            if (likeMessageDTO == null) {
                return null;
            } else {
                return objectMapper.writeValueAsBytes(likeMessageDTO);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public byte[] serialize(String topic, Headers headers, LikeMessageDTO data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override public void close() {
        Serializer.super.close();
    }
}
