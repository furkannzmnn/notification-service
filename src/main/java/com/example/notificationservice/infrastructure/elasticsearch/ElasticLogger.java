package com.example.notificationservice.infrastructure.elasticsearch;

import java.io.IOException;
import java.util.Map;

public interface ElasticLogger {
    void log(String data, String index) throws IOException;

    void log(Map<String, ?> data, String index);
}
