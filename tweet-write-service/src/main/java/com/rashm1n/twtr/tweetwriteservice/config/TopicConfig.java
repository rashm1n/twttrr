package com.rashm1n.twtr.tweetwriteservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    @Bean
    public NewTopic tweetTopic() {
        return TopicBuilder.name("tweet").partitions(3).replicas(3).build();
    }
}
