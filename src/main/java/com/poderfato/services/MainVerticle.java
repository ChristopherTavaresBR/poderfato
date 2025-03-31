package com.poderfato;

import com.poderfato.services.KafkaService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.AsyncResult; 
import io.vertx.core.Handler;    
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.kafka.client.producer.RecordMetadata; 
import java.util.Map;

public class MainVerticle extends AbstractVerticle {
    private final KafkaProducer<String, String> producer;

    public KafkaService(Vertx vertx) {
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("acks", "1");
        
        this.producer = KafkaProducer.create(vertx, config);
    }

    public void sendMessage(String topic, String key, String message, Handler<AsyncResult<RecordMetadata>> handler) {
        KafkaProducerRecord<String, String> record = KafkaProducerRecord.create(topic, key, message);
        producer.send(record, handler);
    }

    public void close() {
        producer.close();
    }
}