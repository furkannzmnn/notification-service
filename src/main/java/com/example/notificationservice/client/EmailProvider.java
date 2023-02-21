package com.example.notificationservice.client;

import com.example.notificationservice.dto.EmailRequest;
import org.springframework.stereotype.Component;

@Component
public final class EmailProvider  implements NotificationProvider<EmailRequest>{

    @Override
    public void sendNotification(EmailRequest request) {
        System.out.println("send email");
    }

    @Override
    public boolean strategyType(String type) {
        return ProviderType.valueOf(type) == ProviderType.EMAIL;
    }
}
