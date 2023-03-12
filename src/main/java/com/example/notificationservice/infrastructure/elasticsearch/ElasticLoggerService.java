package com.example.notificationservice.infrastructure.elasticsearch;

import org.apache.kafka.common.protocol.types.Field;
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
public class ElasticLoggerService implements ElasticLogger {

    private final RestHighLevelClient elasticsearchClient;

    public ElasticLoggerService(ElasticClient elasticClient) {
        this.elasticsearchClient = elasticClient.getElasticSearchClient();
    }

    private void insert(String log, String index) throws IOException {
        String timestamp = initTimestamp();

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("message", log);
            builder.field("type ", log);
            builder.field("@timestamp", timestamp);
        }
        builder.endObject();

        IndexRequest request = new IndexRequest(index);
        request.source(builder);
        elasticsearchClient.index(request, RequestOptions.DEFAULT);
    }

    public static String initTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return now.format(formatter);
    }

    public void insertBulk(Map<String, ?> logs, String index) {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();

            {
                builder.map(logs);
            }
            IndexRequest request = new IndexRequest(index);
            request.source(builder);
            elasticsearchClient.index(request, RequestOptions.DEFAULT);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void log(String data, String index) throws RuntimeException, IOException {
        insert(data, index);
    }

    @Override
    public void log(Map<String, ?> data, String index) {
        insertBulk(data, index);
    }

}
