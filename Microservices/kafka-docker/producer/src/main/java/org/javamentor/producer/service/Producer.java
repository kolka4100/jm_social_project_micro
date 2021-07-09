package org.javamentor.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Producer {
    public static final String topic = "mytopic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemp;

    public void publishToTopic(String message) {
        System.out.print("Producer send: ");
        System.out.println(message);
        this.kafkaTemp.send(topic, message);
    }

    @PostConstruct
    public void sendString() {
        for (int i = 0; i < 10; i++)
        publishToTopic(Integer.toString(i));
    }
}