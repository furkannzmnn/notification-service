package com.example.notificationservice.queue;

import com.example.notificationservice.dto.SmsRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class MediumSmsConsumerService {

    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(MediumSmsConsumerService.class);

    private final ObjectMapper objectMapper;

    public MediumSmsConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "notification-sms-medium", groupId = "group_id")
    @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 1000, multiplier = 2))
    public void sendSms(String message) throws JsonProcessingException {
        final SmsRequest smsRequest = objectMapper.readValue(message, SmsRequest.class);
        LOGGER.info(smsRequest.getSmsDesc()); // sending sms
    }

    @DltHandler
    public void handleDeadLetterTopic(String message) {
        LOGGER.info("Dead Letter Topic: " + message);
    }
}
