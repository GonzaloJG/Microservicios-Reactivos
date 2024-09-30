package com.microservicio.envios.services;

import com.microservicio.envios.models.Shipping;
import reactor.core.publisher.Flux;

public interface ShippingService {
    Flux<Shipping> pendingShipments();
}
