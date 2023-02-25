package com.example.notificationservice.controller;

import com.example.notificationservice.dto.SmsKeyName;
import com.example.notificationservice.dto.SmsTemplateSort;
import com.example.notificationservice.infrastructure.logging.Logger;
import com.example.notificationservice.model.SmsTemplate;
import com.example.notificationservice.service.SmsTemplateSaveService;
import com.example.notificationservice.template.SmsTemplateResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping()
public class SmsController {

    private final SmsTemplateResolver smsTemplateResolver;
    private final SmsTemplateSaveService smsTemplateSaveService;

    public SmsController(SmsTemplateResolver smsTemplateResolver, SmsTemplateSaveService smsTemplateSaveService) {
        this.smsTemplateResolver = smsTemplateResolver;
        this.smsTemplateSaveService = smsTemplateSaveService;
    }

    @PostMapping("/sms")
    public void sendSms(SmsKeyName smsKeyName) {
        final Map<String, Object> build = new SmsTemplateSort().firstName("John").lastName("Doe").build();
        smsTemplateResolver.resolveSmsType(SmsKeyName.ACCOUNT_DELETE_SMS).sendSms("+84988888888", build);
    }

    @GetMapping("/sms-create")
    public ResponseEntity<?> createSms() {
       return ResponseEntity.ok(smsTemplateSaveService.createOrUpdate(SmsTemplate.builder()
               .keyword("firstName,lastName")
               .description("Hello ${firstName} ${lastName}")
               .key(SmsKeyName.USER_OTP_SMS.name())
               .build()));
    }
}
