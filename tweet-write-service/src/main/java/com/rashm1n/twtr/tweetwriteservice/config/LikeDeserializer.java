package com.rashm1n.twtr.tweetwriteservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rashm1n.twtr.tweetwriteservice.model.message.LikeMessageDTO;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class LikeDeserializer implements Deserializer<LikeMessageDTO> {
    ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

    @Override public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override public LikeMessageDTO deserialize(String s, byte[] bytes) {
        try {
            if (bytes==null) {
                return null;
            }
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), LikeMessageDTO.class);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }

    @Override public LikeMessageDTO deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override public void close() {
        Deserializer.super.close();
    }
}
