package com.app.example.kafka.services;

import com.app.example.kafka.models.Element;
import reactor.core.publisher.Flux;

public interface ElementService {
    Flux<Element> elemetsByPrice(double priceMax);
}
