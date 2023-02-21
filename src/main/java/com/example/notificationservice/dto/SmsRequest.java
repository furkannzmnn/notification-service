package com.example.notificationservice.dto;

import com.example.notificationservice.client.PriorityType;

public final class SmsRequest extends BaseRequest {
    private String phoneNumber;
    private String smsDesc;
    private String smsKey;
    private PriorityType priorityType;


    public SmsRequest(String phoneNumber, String smsDesc, String smsKey) {
        this.phoneNumber = phoneNumber;
        this.smsDesc = smsDesc;
        this.smsKey = smsKey;
    }

    public SmsRequest() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSmsDesc() {
        return smsDesc;
    }

    public String getSmsKey() {
        return smsKey;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }
}
