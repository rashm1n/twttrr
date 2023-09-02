package com.rashm1n.twtr.tweetwriteservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@TestConfiguration
@Profile("Test")
public class TestKafkaConfig {
    @Value("${tweet-write-svc.kafka.topic}")
    private String topic;
    @Bean
    public NewTopic tweetTopic() {
        return TopicBuilder.name(topic).partitions(1).replicas(1).build();
    }
}
