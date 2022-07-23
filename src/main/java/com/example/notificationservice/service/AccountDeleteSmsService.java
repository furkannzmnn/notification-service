package com.example.notificationservice.service;

import com.example.notificationservice.client.ProviderType;
import com.example.notificationservice.dto.SmsKeyName;
import com.example.notificationservice.model.SmsTemplate;
import com.example.notificationservice.template.SmsTemplateService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AccountDeleteSmsService extends SmsTemplateService {
    @Override
    public String generateSms(Map<String, Object> data) {
        final SmsTemplate smsTemplate = smsRepository.findByKey(SmsKeyName.USER_OTP_SMS.name()).orElseThrow(RuntimeException::new);
        return formatSms(smsTemplate.getDescription(), data);
    }

    @Override
    public boolean support(SmsKeyName smsKeyName) {
        return SmsKeyName.ACCOUNT_DELETE_SMS == smsKeyName;
    }

    @Override
    public ProviderType type() {
        return ProviderType.SMS;
    }

    @Override
    public SmsKeyName getKey() {
        return SmsKeyName.ACCOUNT_DELETE_SMS;
    }
}
