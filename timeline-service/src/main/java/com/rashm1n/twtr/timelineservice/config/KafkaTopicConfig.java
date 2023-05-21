package com.rashm1n.twtr.timelineservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    NewTopic fanoutTopic() {
        return TopicBuilder.name("fanout").partitions(3).replicas(3).build();
    }
}
