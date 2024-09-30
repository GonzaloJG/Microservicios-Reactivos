package com.microservicio.envios.controllers;

import com.microservicio.envios.models.Shipping;
import com.microservicio.envios.services.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @GetMapping("/shipping")
    public ResponseEntity<Flux<Shipping>> pendingShipments(){
        return new ResponseEntity<>(shippingService.pendingShipments(), HttpStatus.OK);
    }
}
