package com.nrkt.springbootkafkaex.producer;

import com.nrkt.springbootkafkaex.model.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.UUID;

@Component
@Slf4j
public class DispatcherImpl implements Dispatcher {

    @Value("${kafka.topics}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public DispatcherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        KafkaMessage message = KafkaMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .date(LocalDate.now())
                .build();

        var record = new ProducerRecord<>(topicName, UUID.randomUUID().toString(), message.toString());

        record.headers().add(KafkaHeaders.MESSAGE_KEY, message.getId().toString().getBytes(StandardCharsets.UTF_8));

        ListenableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(record);

        sendResult.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, String> sendResult) {
                log.info("Message Sent: " + sendResult.getProducerRecord().value()
                        + " for topic: " + sendResult.getProducerRecord().topic()
                        + " with key: " + sendResult.getProducerRecord().key()
                        + " partition: " + sendResult.getRecordMetadata().partition()
                        + " time: " + sendResult.getRecordMetadata().timestamp()
                        + " offset: " + sendResult.getRecordMetadata().offset()
                );
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }
        });
    }
}


