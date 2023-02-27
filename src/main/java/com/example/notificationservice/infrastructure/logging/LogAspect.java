package com.example.notificationservice.infrastructure.logging;

import com.example.notificationservice.infrastructure.elasticsearch.ElasticLoggerService;
import com.example.notificationservice.model.Log;
import com.example.notificationservice.repository.LogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static com.example.notificationservice.infrastructure.elasticsearch.ElasticLoggerService.initTimestamp;

@Aspect
@Component
public class LogAspect {

    private final LogRepository logRepository;

    public LogAspect(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Around(value = "@annotation(com.example.notificationservice.infrastructure.logging.Logger)")
    public void log(ProceedingJoinPoint joinPoint) throws IllegalAccessException, IOException {
        String methodMessage = getMethodMessage(joinPoint);
        Map<String, String> logElements = new HashMap<>();
        Set<String> displayFields = new HashSet<>();

        for (Field field : joinPoint.getArgs()[0].getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Logger.class)) {
                Logger fieldLogger = field.getAnnotation(Logger.class);
                if (checkIsShowDataEnabled(fieldLogger)) {
                    displayFields.add(field.getName());
                }
                logElements.put(getFieldValue(field), field.get(joinPoint.getArgs()[0]).toString());
            }
        }

        String returnJsonData = joinPoint.getArgs()[0].toString();
        String timestamp = initTimestamp();


        List<Log> logs = new ArrayList<>();

        CompletableFuture.runAsync(() -> {
            String log = String.format("%s %s %s %s", timestamp, methodMessage, logElements, returnJsonData) + new Random().nextInt();
            logs.add(new Log(log));
            logRepository.saveAll(logs);
        });
    }

    @After("@annotation(com.example.notificationservice.infrastructure.logging.Logger)")
    // log after method execution
    public void logAfter(JoinPoint joinPoint) throws IOException {
        String methodMessage = getMethodMessage(joinPoint);
        String returnJsonData = joinPoint.getArgs()[0].toString();

    }

    private static String getMethodMessage(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Logger aspectLogger = method.getAnnotation(Logger.class);
        return aspectLogger.value();
    }

    private static String getFieldValue(Field field) {
        String fieldValue = field.getAnnotation(Logger.class).value();
        return fieldValue.isEmpty() ? field.getName() : fieldValue;
    }

    private static boolean checkIsShowDataEnabled(Logger field) {
        return field.showData();
    }
}
