package com.example.notificationservice.service;

import com.example.notificationservice.model.SmsTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SmsSearchOperation implements SearchOperation<List<SmsTemplate>> {
    private final ElasticsearchOperations elasticsearchOperations;

    public SmsSearchOperation(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<SmsTemplate> search(Query query, int page, int size) {
        query.setPageable(Pageable.ofSize(size).withPage(page));

        {
            query.addFields("id", "key", "description", "keyword", "targetClient");
            query.setRequestCache(true);
        }

        return elasticsearchOperations.search(query, SmsTemplate.class)
               .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }
}
