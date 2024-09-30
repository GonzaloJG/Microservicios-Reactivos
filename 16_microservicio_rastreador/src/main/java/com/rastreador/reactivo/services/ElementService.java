package com.rastreador.reactivo.services;

import com.rastreador.reactivo.models.Element;
import reactor.core.publisher.Flux;

public interface ElementService {
    Flux<Element> findByPriceMax(double priceMax);
}
