package com.example.notificationservice.model;

import com.example.notificationservice.infrastructure.logging.Logger;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "sms_template")
public class SmsTemplate {
    private String id;
    @Logger(value = "KEY", showData = true)
    private String key;
    @Logger(value = "DESCRIPTION", showData = true)
    private String description;
    @Logger(value = "KEYWORD", showData = true)
    private String keyword;
    private String targetClient;

    public SmsTemplate(Builder builder) {
        this.id = builder.id;
        this.key = builder.key;
        this.description = builder.description;
        this.targetClient = builder.targetClient;
        this.keyword = builder.keyword;
    }

    public SmsTemplate() {
        this.id = null;
        this.key = null;
        this.description = null;
        this.targetClient = null;
        this.keyword = null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setTargetClient(String targetClient) {
        this.targetClient = targetClient;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public String getTargetClient() {
        return targetClient;
    }

    public String getKeyword() {
        return keyword;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static <R> void add(R r, SmsTemplate smsTemplate) {
        smsTemplate.setId(r.toString());
    }

    public static final class Builder {
        private String id;
        private String key;
        private String description;
        private String targetClient;
        private String keyword;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder targetClient(String targetClient) {
            this.targetClient = targetClient;
            return this;
        }
        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public SmsTemplate build() {
            return new SmsTemplate(this);
        }
    }
}
