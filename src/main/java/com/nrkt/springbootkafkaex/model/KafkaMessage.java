package com.nrkt.springbootkafkaex.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class KafkaMessage {
    String message;
    UUID id;
    LocalDate date;
}
