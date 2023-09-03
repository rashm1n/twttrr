package com.rashm1n.twtr.likeservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("!Test")
public class TopicConfig {
    @Value("${like-svc.kafka.topic}")
    private String topic;

    @Bean
    public NewTopic likeTopic() {
        return TopicBuilder.name(topic).partitions(3).replicas(3).build();
    }

}
