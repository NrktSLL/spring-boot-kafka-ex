package com.nrkt.springbootkafkaex.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

    @KafkaListener(topics = "${kafka.topics}",
            clientIdPrefix = "consume1",
            containerFactory = "kafkaListenerStringContainerFactory")
    public void consume1(ConsumerRecord<String, String> record) {

        log.info("Consume 1 Received Message: "
                + record.value()
                + " from partition: "
                + record.partition()
                + "with header: "
                + record.headers()
                + " time: "
                + record.timestamp()
        );
    }

    @KafkaListener(topics = "${kafka.topics}",
            clientIdPrefix = "consume2",
            containerFactory = "kafkaListenerStringContainerFactory")
    public void consume2(ConsumerRecord<String, String> record) {

        log.info("Consume 2 Received Message: "
                + record.value()
                + " from partition: "
                + record.partition()
                + "with header: "
                + record.headers()
                + " time: "
                + record.timestamp()
        );
    }
}