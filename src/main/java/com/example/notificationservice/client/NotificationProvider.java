package com.example.notificationservice.client;

public interface NotificationProvider<T> {
    void sendNotification(T request);
    boolean strategyType(String type);
}
