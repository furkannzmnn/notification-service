package com.example.notificationservice.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "user_info")
public class User {

    private final Long id;
    private final String userName;

    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
