package com.example.notificationservice.repository;

import com.example.notificationservice.model.Log;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface LogRepository extends ElasticsearchRepository<Log, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"message\": \"?0\"}}]}}")
    List<Log> findByMessage(String message);
}
