package com.example.notificationservice.template;

import com.example.notificationservice.dto.SmsKeyName;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class SmsTemplateResolver {

    private final List<SmsTemplateService> smsTemplateServices;

    public SmsTemplateResolver(List<SmsTemplateService> smsTemplateServices) {
        this.smsTemplateServices = smsTemplateServices;
    }

    public SmsTemplateService resolveSmsType(SmsKeyName smsKeyName) {
       return smsTemplateServices.stream()
                .filter(each -> each.support(smsKeyName))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }
}
