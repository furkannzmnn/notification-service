package com.example.notificationservice.client;

import com.example.notificationservice.dto.BaseRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationProviderFactory {
    private final List<NotificationProvider<?>> notificationProvider;

    public NotificationProviderFactory(List<NotificationProvider<?>> notificationProvider) {
        this.notificationProvider = notificationProvider;
    }

    public NotificationProvider<BaseRequest> get(ProviderType providerType) {
        return (NotificationProvider<BaseRequest>) notificationProvider.stream()
                .filter(each -> each.strategyType(providerType.name()))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }
}
