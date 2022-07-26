package com.example.notificationservice.client;

import com.example.notificationservice.dto.SmsRequest;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Pattern;

@Component
public final class SmsCommonNotification implements CommonNotificationService<SmsRequest> {

    private final ThreadPoolExecutor threadPoolTaskScheduler;
    public SmsCommonNotification(ThreadPoolExecutor threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    @Override
    public void validateNotification(SmsRequest notification) {
        final String number = notification.getPhoneNumber();
        if (number == null || number.isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }

        if (number.length() < 10) {
            throw new IllegalArgumentException("Phone number must be 10 digits");
        }

        if (number.length() > 17) {
            throw new IllegalArgumentException("Phone number must be 17 digits");
        }

        if (number.startsWith("0")) {
            throw new IllegalArgumentException("Phone number must not start with 0");
        }
    }

    @Override
    public void scheduleNotification(Runnable runnable) {
        threadPoolTaskScheduler.execute(runnable);
    }

    @Override
    public PriorityType priorityNotification(PriorityType priorityType) {
        return priorityType;
    }
}
