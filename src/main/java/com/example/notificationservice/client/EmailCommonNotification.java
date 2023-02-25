package com.example.notificationservice.client;

import com.example.notificationservice.dto.EmailRequest;

public class EmailCommonNotification implements CommonNotificationService<EmailRequest>{
    @Override
    public void validateNotification(EmailRequest notification) {

    }

    @Override
    public void scheduleNotification(Runnable runnable) {

    }

    @Override
    public PriorityType priorityNotification(PriorityType priorityType) {
        return priorityType;
    }
}
