//package com.rashm1n.twtr.followerservice.consumer;
//
//import com.rashm1n.twtr.followerservice.model.message.UserFollowerMessageDTO;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class KafkaConsumer {
//    @Bean
//    public
//    Map<String, Object> consumerProps() {
//        HashMap<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, UserFollowerDeserializer.class);
//        return props;
//    }
//
//    @Bean
//    public ConsumerFactory<String, UserFollowerMessageDTO> defaultKafkaConsumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerProps());
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, UserFollowerMessageDTO> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, UserFollowerMessageDTO> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
//        concurrentKafkaListenerContainerFactory.setConsumerFactory(defaultKafkaConsumerFactory());
//        return concurrentKafkaListenerContainerFactory;
//    }
//}
