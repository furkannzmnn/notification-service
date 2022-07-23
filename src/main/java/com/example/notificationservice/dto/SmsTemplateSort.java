package com.example.notificationservice.dto;

import java.util.HashMap;
import java.util.Map;

public class SmsTemplateSort {
    private Map<String, Object> map = new HashMap<>();


    public SmsTemplateSort firstName(String firstName) {
        map.put("firstName", firstName);
        return this;
    }

    public SmsTemplateSort lastName(String lastName) {
        map.put("lastName", lastName);
        return this;
    }

    public SmsTemplateSort email(String email) {
        map.put("email", email);
        return this;
    }

    public SmsTemplateSort phone(String phone) {
        map.put("phone", phone);
        return this;
    }

    public SmsTemplateSort address(String address) {
        map.put("address", address);
        return this;
    }

    public SmsTemplateSort city(String city) {
        map.put("city", city);
        return this;
    }


    public Map<String, Object> build() {
        return map;
    }

}
