package com.example.notificationservice.client;

import com.example.notificationservice.dto.SmsRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MediumSmsQueue implements SmsPriorityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MediumSmsQueue.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public MediumSmsQueue(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean priorityNotification(PriorityType type) {
        return type == PriorityType.MEDIUM || type == PriorityType.LOW;
    }

    @Override
    public void sendQueue(SmsRequest request) {
        LOGGER.info("Sending SMS notification to queue : {}", request.getPhoneNumber());
        try {
            kafkaTemplate.send("notification-sms-medium",  objectMapper.writeValueAsString(request))
                    .addCallback(
                            (success) -> LOGGER.info("SMS notification sent to queue : {}", request.getPhoneNumber()),
                            (failure) -> LOGGER.error("SMS notification failed to send to queue : {}", request.getPhoneNumber()));

        } catch (JsonProcessingException e) {
            LOGGER.error("Error while sending SMS notification to queue : {}", e.getMessage());
        }
    }
}
