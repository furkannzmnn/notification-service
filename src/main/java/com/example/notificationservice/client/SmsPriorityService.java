package com.example.notificationservice.client;

import com.example.notificationservice.dto.SmsRequest;

public interface SmsPriorityService {
    boolean priorityNotification(PriorityType type);
    void sendQueue(SmsRequest smsRequest);
}
