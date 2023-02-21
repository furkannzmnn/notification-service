package com.example.notificationservice.infrastructure.kafka;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConsumerRebalanceListenerImpl implements ConsumerRebalanceListener {

    private static final Logger LOGGER = LogManager.getLogger(ConsumerRebalanceListenerImpl.class);

    private final Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
    private final KafkaConsumer kafkaConsumer;

    public ConsumerRebalanceListenerImpl(KafkaConsumer kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    public void addOffSetTopic(String topic, int partition, long offset) {
        currentOffsets.put(new TopicPartition(topic, partition), new OffsetAndMetadata(offset + 1 , null));
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> collection) {
        LOGGER.info("Partitions revoked");
        LOGGER.info("COMMİTİNG offsets: " + currentOffsets);
        kafkaConsumer.consumerFactory().createConsumer().commitSync(currentOffsets);

    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> collection) {
        LOGGER.info("onPartitionsAssigned callback triggered");

    }

    public Map<TopicPartition, OffsetAndMetadata> getCurrentOffsets() {
        return currentOffsets;
    }
}
