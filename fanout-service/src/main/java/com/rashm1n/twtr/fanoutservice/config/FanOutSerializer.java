package com.rashm1n.twtr.fanoutservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rashm1n.twtr.fanoutservice.model.FanOutMessageDTO;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Map;

public class FanOutSerializer implements Serializer<FanOutMessageDTO> {
    ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, FanOutMessageDTO fanOutMessageDTO) {
        try {
            if (fanOutMessageDTO == null) {
                return null;
            } else {
                return objectMapper.writeValueAsBytes(fanOutMessageDTO);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override public byte[] serialize(String topic, Headers headers, FanOutMessageDTO data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override public void close() {
        Serializer.super.close();
    }
}
