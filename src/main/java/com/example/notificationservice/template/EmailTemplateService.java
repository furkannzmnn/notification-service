package com.example.notificationservice.template;

import com.example.notificationservice.client.NotificationProviderFactory;
import com.example.notificationservice.client.ProviderType;
import com.example.notificationservice.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public abstract class EmailTemplateService {
    @Autowired
    private NotificationProviderFactory providerFactory;

    public void sendEmail(Map<String, Object> data) {
        final String email = generateEmail(data);
        final String key = getKey().name();
        providerFactory.get(ProviderType.EMAIL).sendNotification(new EmailRequest(email));
    }

    public abstract String generateEmail(Map<String, Object> data); // some business logic
    public abstract EmailKeyName getKey();
    public abstract boolean support(EmailKeyName emailKeyName);


}
