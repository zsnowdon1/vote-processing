package com.voting.vote_processing.service;

import com.voting.vote_processing.entity.SurveyRequest;
import org.springframework.stereotype.Service;

@Service
public interface KafkaConsumerService {
    void kafkaConsumer(Object message, SurveyRequest vote);
}
