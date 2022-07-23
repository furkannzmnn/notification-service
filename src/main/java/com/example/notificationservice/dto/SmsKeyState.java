package com.example.notificationservice.dto;

import java.util.EnumSet;
import java.util.function.Predicate;

public class SmsKeyState {
    private static Predicate<SmsKeyName> isHighLevelSms = (keyName) -> EnumSet.of(SmsKeyName.ACCOUNT_DELETE_SMS).contains(keyName);

    public static boolean isHighLevelSms(SmsKeyName keyName) {
        return isHighLevelSms.test(keyName);
    }
}
