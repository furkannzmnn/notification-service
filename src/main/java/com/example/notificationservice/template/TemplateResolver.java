package com.example.notificationservice.template;

import com.example.notificationservice.client.ProviderType;
import com.example.notificationservice.dto.SmsKeyName;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class TemplateResolver {

    private static final String SMS= "sms_key_name";
    private static final String EMAIL= "email_key_name";

    private final SmsTemplateResolver smsTemplateResolver;
    private final EmailTemplateResolver emailTemplateResolver;

    public TemplateResolver(SmsTemplateResolver smsTemplateResolver, EmailTemplateResolver emailTemplateResolver) {
        this.smsTemplateResolver = smsTemplateResolver;
        this.emailTemplateResolver = emailTemplateResolver;
    }

    public void resolve(ProviderType type, Map<String, String> params) {
       if (type == ProviderType.SMS) {
           if (params.containsKey(SMS)) {
               smsTemplateResolver.resolveSmsType(SmsKeyName.valueOf(params.get(SMS)));
           }
       }

         if (type == ProviderType.EMAIL) {
              if (params.containsKey(EMAIL)) {
                emailTemplateResolver.resolveEmailType(EmailKeyName.valueOf(params.get(EMAIL)));
              }
         }

    }

}
