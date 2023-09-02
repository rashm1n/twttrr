package com.rashm1n.twtr.tweetwriteservice;

import com.rashm1n.twtr.tweetwriteservice.controller.TweetController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.util.function.SupplierUtils;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
@ActiveProfiles("Test")
@Import(TestKafkaConfig.class)
class TweetWriteServiceApplicationTests {

    @Container
    static final KafkaContainer kafka = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:latest")
    ).withKraft();

    @Container
    static final OracleContainer oracle = new OracleContainer(
            DockerImageName.parse("gvenzl/oracle-xe:21-slim-faststart")
    ).withDatabaseName("testDB").withUsername("testUser").withPassword("testPwd");

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
        registry.add("spring.datasource.url", oracle::getJdbcUrl);
        registry.add("spring.datasource.username", oracle::getUsername);
        registry.add("spring.datasource.password", oracle::getPassword);
        registry.add("tweet-write-svc.kafka.topic", ()->"tweet");
        registry.add("server.port", ()->8080);
    }

    @Autowired
    private TweetController controller;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }

}
