package com.example.notificationservice.template;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailTemplateResolver {
    private final List<EmailTemplateService> smsTemplateServices;

    public EmailTemplateResolver(List<EmailTemplateService> smsTemplateServices) {
        this.smsTemplateServices = smsTemplateServices;
    }

    public EmailTemplateService resolveEmailType(EmailKeyName emailKeyName) {
        return smsTemplateServices.stream()
                .filter(each -> each.support(emailKeyName))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }
}
