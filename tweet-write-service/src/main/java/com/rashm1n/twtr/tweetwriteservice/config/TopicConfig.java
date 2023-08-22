package com.rashm1n.twtr.tweetwriteservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    @Value("${tweet-write-svc.kafka.topic}")
    private String topic;
    @Bean
    public NewTopic tweetTopic() {
        return TopicBuilder.name(topic).partitions(3).replicas(3).build();
    }
}
