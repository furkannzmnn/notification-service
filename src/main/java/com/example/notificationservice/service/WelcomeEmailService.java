package com.example.notificationservice.service;

import com.example.notificationservice.template.EmailKeyName;
import com.example.notificationservice.template.EmailTemplateService;

import java.util.Map;

public class WelcomeEmailService extends EmailTemplateService {
    @Override
    public String generateEmail(Map<String, Object> data) {
        return null;
    }

    @Override
    public EmailKeyName getKey() {
        return null;
    }

    @Override
    public boolean support(EmailKeyName emailKeyName) {
        return EmailKeyName.WELCOME_EMAIL == emailKeyName;
    }
}
