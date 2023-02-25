package com.example.notificationservice.dto;

import java.util.EnumSet;
import java.util.function.Predicate;

public class SmsKeyState {
    private static final Predicate<SmsKeyName> HIGH_LEVEL_SMS = (keyName) -> EnumSet.of(SmsKeyName.ACCOUNT_DELETE_SMS).contains(keyName);

    public static boolean isHighLevelSms(SmsKeyName keyName) {
        return HIGH_LEVEL_SMS.test(keyName);
    }
}
