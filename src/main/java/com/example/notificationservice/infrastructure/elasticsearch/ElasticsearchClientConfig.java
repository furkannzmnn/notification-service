
package com.example.notificationservice.infrastructure.elasticsearch;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import reactor.util.annotation.NonNullApi;

@Configuration
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {
    @Value("${elasticsearch.host}")
    private String host;
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        System.out.println("ELASTIC LOG BAK BURAYAAAAA = " + host);
        final ClientConfiguration clientConfiguration = ClientConfiguration
                .builder()
                .connectedTo(host)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }




}

