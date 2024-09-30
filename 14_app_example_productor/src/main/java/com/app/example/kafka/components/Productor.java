package com.app.example.kafka.components;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Productor {
    private KafkaProducer<String, String> producer;

    public Productor() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public void send(String topic, String message) {
        producer.send(new ProducerRecord<>(topic, message));
    }

    public void close() {
        producer.close();
    }
}
