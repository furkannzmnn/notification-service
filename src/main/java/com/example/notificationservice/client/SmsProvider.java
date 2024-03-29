package com.example.notificationservice.client;

import com.example.notificationservice.dto.SmsKeyName;
import com.example.notificationservice.dto.SmsKeyState;
import com.example.notificationservice.dto.SmsRequest;
import com.example.notificationservice.service.SlackReportingClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmsProvider implements NotificationProvider<SmsRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsProvider.class);

    private final List<SmsPriorityService> smsPriorityService;
    @Qualifier(value = "smsCommonNotification")
    private final CommonNotificationService<SmsRequest> notificationService;
    private final SlackReportingClient slackReportingService;

    public SmsProvider(List<SmsPriorityService> smsPriorityService, CommonNotificationService<SmsRequest> notificationService, SlackReportingClient slackReportingService) {
        this.smsPriorityService = smsPriorityService;
        this.notificationService = notificationService;
        this.slackReportingService = slackReportingService;
    }

    @Override
    public void sendNotification(SmsRequest request) {
        try {
            final SmsKeyName keyName = SmsKeyName.valueOf(request.getSmsKey());
            final boolean highLevelSms = SmsKeyState.isHighLevelSms(keyName);
            final PriorityType type = notificationService.priorityNotification(highLevelSms ? PriorityType.HIGH : PriorityType.LOW);
            LOGGER.info("Sending SMS notification with priority: {}", type);
            request.setPriorityType(type);

            notificationService.validateNotification(request);
            notificationService.scheduleNotification(() -> sendSms(request));
        }catch (Exception e) {
            slackReportingService.sendSlackMessage(e.getMessage());
        }
    }

    @Override
    public boolean strategyType(String type) {
        return ProviderType.valueOf(type) == ProviderType.SMS;
    }

    public void sendSms(SmsRequest request) {
        smsPriorityService.stream()
                .filter(smsPriorityService -> smsPriorityService.priorityNotification(request.getPriorityType()))
                .findFirst()
                .ifPresent(smsPriorityService -> smsPriorityService.sendQueue(request));
    }
}
