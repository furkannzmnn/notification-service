package com.example.notificationservice.infrastructure.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

@Component
public class ElasticClient {
    private final RestHighLevelClient elasticSearchClient;

    public ElasticClient() {
        this.elasticSearchClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("elasticsearch", 9200, "http"),
                        new HttpHost("elasticsearch", 9201, "http")
                ));
    }

    public RestHighLevelClient getElasticSearchClient() {
        return elasticSearchClient;
    }

}
