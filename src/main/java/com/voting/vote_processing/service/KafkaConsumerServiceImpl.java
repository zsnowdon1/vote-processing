package com.voting.vote_processing.service;

import com.voting.vote_processing.entity.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    @Override
    @KafkaListener(topics = "votes-topic", groupId = "${spring.kafka.consumer.group-id}")
    public void kafkaConsumer(Object message, Vote vote) {
        logger.info("Received kafka message: {}", vote.toString());
    }
}
