package com.example.notificationservice.service;


import org.springframework.data.elasticsearch.core.query.Query;

public interface SearchOperation<T> {
    T search(Query query, int page, int size);

    default T search(Query query) {
        return search(query, 0, 10);
    }
}
