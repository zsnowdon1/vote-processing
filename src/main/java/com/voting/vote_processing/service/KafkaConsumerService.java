package com.voting.vote_processing.service;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public interface KafkaConsumerService {
    void kafkaConsumer(String message, Acknowledgment acknowledgment);
}
