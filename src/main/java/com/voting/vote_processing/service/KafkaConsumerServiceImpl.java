package com.voting.vote_processing.service;

import com.voting.vote_processing.entity.SelectedChoice;
import com.voting.vote_processing.entity.SurveyRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    @Autowired
    public KafkaConsumerServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void kafkaConsumer(Object message, SurveyRequest vote) {
        logger.info("Received kafka message " + vote.toString());
        long surveyId = vote.getSurveyId();
        for(SelectedChoice choice: vote.getResponses()) {
            logger.info("Processing vote for question " + choice.getQuestionId());
            String questionId = Long.toString(choice.getQuestionId());
            String choiceId = Long.toString(choice.getChoiceId());

            String redisKey = "survey:" + surveyId + ":question:" + questionId + ":results";
            logger.info("Incrementing votes for choice:" + choiceId);
            redisTemplate.opsForHash().increment(redisKey, choiceId, 1);
            logger.info("Processed vote for survey: " + surveyId + ", question: " + questionId + ", choice: " + choiceId);
        }
    }
}
