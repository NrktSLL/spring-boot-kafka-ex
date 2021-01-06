package com.nrkt.springbootkafkaex.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    @Profile("EmbeddedKafka") //for testing
    EmbeddedKafkaBroker broker() {
        return new EmbeddedKafkaBroker(1)
                .kafkaPorts(9092)
                .brokerListProperty(bootstrapServers);
    }
}
