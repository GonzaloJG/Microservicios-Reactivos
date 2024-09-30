package com.rastreador.reactivo.controllers;

import com.rastreador.reactivo.models.Element;
import com.rastreador.reactivo.services.ElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ElementController {

    private final ElementService elementService;

    @GetMapping("/elements/{price}")
    public ResponseEntity<Flux<Element>> elementsByPrice(@PathVariable("price") double priceMax){
        return new ResponseEntity<>(elementService.findByPriceMax(priceMax), HttpStatus.OK);
    }
}
