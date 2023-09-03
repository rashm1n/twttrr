package com.rashm1n.twtr.tweetwriteservice.config;

import com.rashm1n.twtr.tweetwriteservice.model.message.LikeMessageDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServerURL;

    @Value("${tweet-write-svc.kafka.client-id}")
    private String clientID;

    @Bean
    public Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServerURL);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,LikeDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"tweet-write-service");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG,clientID);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        return props;
    }

    @Bean
    public ConsumerFactory<String, LikeMessageDTO> defaultKafkaConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LikeMessageDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, LikeMessageDTO> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(defaultKafkaConsumerFactory());
        return concurrentKafkaListenerContainerFactory;
    }
}
