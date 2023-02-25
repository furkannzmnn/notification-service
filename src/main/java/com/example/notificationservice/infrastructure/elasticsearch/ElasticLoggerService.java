package com.example.notificationservice.infrastructure.elasticsearch;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class ElasticLoggerService {

    private final RestHighLevelClient elasticsearchClient;

    public ElasticLoggerService(ElasticClient elasticClient) {
        this.elasticsearchClient = elasticClient.getElasticSearchClient();
    }

    public void insert(String log) throws IOException {
        String timestamp = initTimestamp();

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("message", log);
            builder.field("type ", log);
            builder.field("@timestamp", timestamp);
        }
        builder.endObject();

        IndexRequest request = new IndexRequest("logs");
        request.source(builder);
        elasticsearchClient.index(request, RequestOptions.DEFAULT);
    }

    public static String initTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return now.format(formatter);
    }

    public void insertBulk(Map<String, ?> logs) {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();

            {
                builder.map(logs);
            }
            IndexRequest request = new IndexRequest("new_logs");
            request.source(builder);
            elasticsearchClient.index(request, RequestOptions.DEFAULT);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
