package com.example.notificationservice.repository;

import com.example.notificationservice.model.SmsTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsRepository extends ElasticsearchRepository<SmsTemplate, String> {

    Optional<SmsTemplate> findByKey(String key);
}
