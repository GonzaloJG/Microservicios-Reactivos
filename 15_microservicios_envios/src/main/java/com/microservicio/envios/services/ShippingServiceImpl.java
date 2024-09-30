package com.microservicio.envios.services;

import com.microservicio.envios.models.Shipping;
import com.microservicio.envios.repositories.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;

    @Override
    public Flux<Shipping> pendingShipments() {
        return shippingRepository.findByStatus("pending");
    }

    @KafkaListener(topics = "orderTopic", groupId = "myGroup2")
    public void shippingManagement(Shipping shipping) {
        shipping.setDate(LocalDateTime.now());
        shipping.setStatus("pending");
        shippingRepository.save(shipping).subscribe();
    }
}
