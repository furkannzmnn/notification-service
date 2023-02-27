package com.example.notificationservice.client;

import com.example.notificationservice.dto.SmsRequest;
import com.example.notificationservice.infrastructure.kafka.ConsumerRebalanceListenerImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MediumSmsQueue implements SmsPriorityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MediumSmsQueue.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final ConsumerRebalanceListenerImpl consumerRebalanceListener;

    public MediumSmsQueue(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper, ConsumerRebalanceListenerImpl consumerRebalanceListener) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.consumerRebalanceListener = consumerRebalanceListener;
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

            consumerRebalanceListener.addOffSetTopic("notification-sms-medium",0,0);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while sending SMS notification to queue : {}", e.getMessage());
        }
    }
}
