package com.example.notificationservice.template;

import com.example.notificationservice.client.NotificationProvider;
import com.example.notificationservice.client.NotificationProviderFactory;
import com.example.notificationservice.client.PriorityType;
import com.example.notificationservice.client.ProviderType;
import com.example.notificationservice.dto.BaseRequest;
import com.example.notificationservice.dto.SmsKeyName;
import com.example.notificationservice.dto.SmsRequest;
import com.example.notificationservice.repository.SmsRepository;
import com.floreysoft.jmte.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component
public abstract class SmsTemplateService {

    @Autowired
    protected SmsRepository smsRepository;
    @Autowired
    private NotificationProviderFactory providerFactory;

    @Transactional
    public void sendSms(String phoneNumber, Map<String, Object> data) {
        final String sms = generateSms(data);
        final ProviderType providerType = type();
        final SmsKeyName key = getKey();
        providerFactory.get(providerType).sendNotification(new SmsRequest(phoneNumber, sms, key.name()));
    }

    public abstract String generateSms(Map<String, Object> data);
    public abstract boolean support(SmsKeyName smsKeyName);
    public abstract ProviderType type();
    public abstract SmsKeyName getKey();

    protected String formatSms(String desc, Map<String, Object> map) {
        return new Engine().transform(desc, map);
    }
}
