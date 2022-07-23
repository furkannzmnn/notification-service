package com.example.notificationservice.template;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public final class SendSmsService {
    public void sendAsyncSms(String phoneNumber, String sms) {
        CompletableFuture.runAsync(() -> {
            System.out.println("sms sender triggered: " + phoneNumber + " " + sms);
        });
    }

}
