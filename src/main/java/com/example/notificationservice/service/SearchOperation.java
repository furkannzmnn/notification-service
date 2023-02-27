package com.example.notificationservice.service;


import org.springframework.data.elasticsearch.core.query.BaseQuery;

public interface SearchOperation<T> {
    T search(BaseQuery query, int page, int size);

    default T search(BaseQuery query) {
        return search(query, 0, 10);
    }

    default T search() {
        return search(new BaseQuery());
    }
}
