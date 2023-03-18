package com.example.notificationservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "SLACK-SERVICE" ,url = "https://hooks.slack.com/services/T01J2QZLQ0C/B01J2QZLQ0C/1")
public interface SlackReportingClient {

    @GetMapping
    Object sendSlackMessage(String message);
}
