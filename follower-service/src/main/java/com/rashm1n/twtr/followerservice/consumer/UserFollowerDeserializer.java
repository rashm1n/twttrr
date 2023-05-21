//package com.rashm1n.twtr.followerservice.consumer;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rashm1n.twtr.followerservice.model.UserFollowersDTO;
//import org.apache.kafka.common.errors.SerializationException;
//import org.apache.kafka.common.header.Headers;
//import org.apache.kafka.common.serialization.Deserializer;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import java.util.Map;
//
//public class UserFollowerDeserializer implements Deserializer<UserFollowersDTO> {
//    ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
//
//    @Override
//    public void configure(Map<String, ?> configs, boolean isKey) {
//        Deserializer.super.configure(configs, isKey);
//    }
//
//    @Override
//    public UserFollowersDTO deserialize(String topic, Headers headers, byte[] data) {
//        return Deserializer.super.deserialize(topic, headers, data);
//    }
//
//    @Override
//    public void close() {
//        Deserializer.super.close();
//    }
//
//    @Override
//    public UserFollowersDTO deserialize(String s, byte[] bytes) {
//        try {
//            if (bytes == null){
//                System.out.println("Null received at deserializing");
//                return null;
//            }
//            return objectMapper.readValue(new String(bytes, "UTF-8"), UserFollowersDTO.class);
//        } catch (Exception e) {
//            throw new SerializationException("Error when deserializing byte[] to MessageDto");
//        }
//    }
//}
