package com.example.notificationservice.infrastructure.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class KafkaBean {
    @Value(value = "${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        System.out.println( "KAFKA BOOTSTRAP SERVERS = " + bootstrapServers);
        Map<String, Object> map = new HashMap<>();
        map.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(map);
    }


    @Bean
    public NewTopic topic() {
        return new NewTopic("notification-sms-medium", 1, (short) 1);
    }


}
