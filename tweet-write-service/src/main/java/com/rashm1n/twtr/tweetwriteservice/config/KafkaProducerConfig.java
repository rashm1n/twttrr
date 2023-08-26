package com.rashm1n.twtr.tweetwriteservice.config;

import com.rashm1n.twtr.tweetwriteservice.model.message.TweetMessageDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServerURL;
    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServerURL);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TweetSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, TweetMessageDTO> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());

    }

    @Bean
    public KafkaTemplate<String, TweetMessageDTO> kafkaTemplate(ProducerFactory<String, TweetMessageDTO> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
