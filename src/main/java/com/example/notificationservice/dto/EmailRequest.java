package com.example.notificationservice.dto;

public class EmailRequest extends BaseRequest {
    private String template;
    private String to;
    private String subject;
    private String from;
    private String cc;

    public EmailRequest(String template) {
        this.template = template;
    }

    public EmailRequest() {
    }
}
