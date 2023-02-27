package com.example.notificationservice.service;

import com.example.notificationservice.model.SmsTemplate;
import org.elasticsearch.search.aggregations.Aggregations;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregation;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.BaseQuery;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SmsSearchOperation implements SearchOperation<List<SmsTemplate>> {
    private final ElasticsearchOperations elasticsearchOperations;

    public SmsSearchOperation(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<SmsTemplate> search(BaseQuery query, int page, int size) {
        query.setPageable(Pageable.ofSize(size).withPage(page));

        {
            query.addFields("id", "key", "description", "keyword", "targetClient");
        }


        return elasticsearchOperations.search(query, SmsTemplate.class)
               .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }


}
@RestController
@RequestMapping("/log")
class Controller {
    private final SearchOperation<List<SmsTemplate>> smsSearchOperation;

    public Controller(SearchOperation<List<SmsTemplate>> smsSearchOperation) {
        this.smsSearchOperation = smsSearchOperation;
    }

    @GetMapping()
    public List<SmsTemplate> searchSmsTemplate() {
        return smsSearchOperation.search(new BaseQuery());
    }
}