package com.example.notificationservice.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class BaseRequest {
    private Date requestTime = new Date();

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
}
