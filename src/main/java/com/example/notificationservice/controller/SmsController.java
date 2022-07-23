package com.example.notificationservice.controller;

import com.example.notificationservice.dto.SmsKeyName;
import com.example.notificationservice.dto.SmsTemplateSort;
import com.example.notificationservice.model.SmsTemplate;
import com.example.notificationservice.service.SmsTemplateSaveService;
import com.example.notificationservice.template.SmsTemplateResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping()
public class SmsController {

    private final SmsTemplateResolver smsTemplateResolver;
    private final SmsTemplateSaveService smsRepository;

    public SmsController(SmsTemplateResolver smsTemplateResolver, SmsTemplateSaveService smsRepository) {
        this.smsTemplateResolver = smsTemplateResolver;
        this.smsRepository = smsRepository;
    }

    @RequestMapping("/sms")
    public void sendSms() {
        final Map<String, Object> build = new SmsTemplateSort().firstName("John").lastName("Doe").build();
        smsTemplateResolver.resolveSmsType(SmsKeyName.USER_OTP_SMS).sendSms("+84988888888", build);
    }

    @PostMapping("/sms-create")
    public ResponseEntity<?> createSms() {
       return ResponseEntity.ok(smsRepository.createOrUpdate(SmsTemplate.builder()
               .keyword("firstName,lastName")
               .description("Hello ${firstName} ${lastName}")
               .key(SmsKeyName.USER_OTP_SMS.name())
               .build()));
    }
}
