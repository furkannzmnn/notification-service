package com.example.notificationservice.client;

public interface CommonNotificationService<T> {
    void validateNotification(T notification);
    void scheduleNotification(Runnable runnable);
    PriorityType priorityNotification(PriorityType priorityType);
}
