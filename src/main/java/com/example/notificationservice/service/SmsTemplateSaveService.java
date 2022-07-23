package com.example.notificationservice.service;

import com.example.notificationservice.model.SmsTemplate;
import com.example.notificationservice.repository.SmsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class SmsTemplateSaveService {

    private static final String PREFIX_LEFT = "{";
    private static final String PREFIX_RIGHT = "}";
    private static final String SEPARATOR = "$";

    private final SmsRepository smsRepository;

    public SmsTemplateSaveService(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    @Transactional
    public SmsCreateResponse<SmsTemplate> createOrUpdate(SmsTemplate request) {

        final SmsTemplate.Builder builder = SmsTemplate.builder();


        final String[] keywords = request.getKeyword().split(",");
        final String collect = Arrays.stream(keywords)
                .map(prefix -> SEPARATOR + PREFIX_LEFT + prefix + PREFIX_RIGHT)
                .collect(Collectors.joining());


        final SmsTemplate smsTemplate = builder
                .description(request.getDescription())
                .key(request.getKey())
                .keyword(collect)
                .targetClient(request.getTargetClient())
                .build();

        smsRepository.save(smsTemplate);

        return new SmsCreateResponse<>(smsTemplate, true);
    }

    private static final class SmsCreateResponse<T> {
        private final T data;
        private final boolean isSuccess;

        private SmsCreateResponse(T data, boolean isSuccess) {
            this.data = data;
            this.isSuccess = isSuccess;
        }

        public T getData() {
            return data;
        }

        public boolean isSuccess() {
            return isSuccess;
        }
    }
}
