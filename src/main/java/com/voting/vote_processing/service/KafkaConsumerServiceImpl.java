package com.voting.vote_processing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voting.vote_processing.entity.SelectedChoice;
import com.voting.vote_processing.entity.SurveyRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ChannelTopic topic;

    private final ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    public KafkaConsumerServiceImpl(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
        this.objectMapper = objectMapper;
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void kafkaConsumer(Object message, SurveyRequest vote) {
        logger.info("Received kafka message " + vote.toString());
        String surveyId = vote.getSurveyId();
        for(SelectedChoice choice: vote.getResponses()) {
            logger.info("Processing vote for question " + choice.getQuestionId());
            String questionId = choice.getQuestionId();
            String choiceId = choice.getChoiceId();

            String redisKey = "survey:" + surveyId + ":question:" + questionId + ":results";
            logger.info("Incrementing votes for choice:" + choiceId);
            long newCount = redisTemplate.opsForHash().increment(redisKey, choiceId, 1);

            HashMap<String, Object> voteData = new HashMap<>();
            voteData.put("choiceId", choice.getChoiceId());
            voteData.put("questionId", choice.getQuestionId());
            voteData.put("votes", newCount);

            try {
                String updateMessage = objectMapper.writeValueAsString(voteData);
                redisTemplate.convertAndSend(topic.getTopic(), updateMessage);
            } catch (Exception e) {
                logger.error("Failed to public vote count update to redis");
            }
            logger.info("Processed vote for survey: " + surveyId + ", question: " + questionId + ", choice: " + choiceId);
        }
    }
}
