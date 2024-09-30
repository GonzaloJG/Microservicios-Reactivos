package com.microservicio.pedidos.productor.services;

import com.microservicio.pedidos.productor.models.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Value("${topic}")
    private String topic;

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public void orderRegister(Order order) {
        CompletableFuture<SendResult<String, Order>> future = kafkaTemplate.send(topic, order);
        future.whenCompleteAsync((r,t) -> {
            if (t!=null)
                throw new RuntimeException(t);
            System.out.println("Se ha registrado el pedido " + r.getProducerRecord().value().toString() + "en el topico " + r.getRecordMetadata().topic() );
        });
    }
}
