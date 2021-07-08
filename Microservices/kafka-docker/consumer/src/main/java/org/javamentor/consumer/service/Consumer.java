package org.javamentor.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "mytopic2", groupId = "mygroup")
    public void consumeFromTopic(String message) {
        System.out.println("Consumer get: " + message);
    }
}