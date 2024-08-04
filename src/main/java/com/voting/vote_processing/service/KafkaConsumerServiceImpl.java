package com.voting.vote_processing.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Override
    @KafkaListener(topics = "votes-topic", groupId = "${spring.kafka.consumer.group-id}")
    public void kafkaConsumer(String message, Acknowledgment acknowledgment) {
        System.out.println("Received message: " + message);
    }
}
