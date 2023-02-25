package com.example.notificationservice.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "logs")
public class Log {
    private String id;
    private String message;

    public Log(String message) {
        this.message = message;
    }

    public Log() {
        this.message = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
