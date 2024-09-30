package com.microservicio.pedidos.consumidor.services;

import com.microservicio.pedidos.consumidor.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductService {
    Flux<Product> catalog();
    Flux<Product> productsByCategory(String category);
    Mono<Product> productByCode(int code);
    Mono<Void> addProduct(Product product);
    Mono<Product> deleteProduct(int code);
    Mono<Product> updateProduct(int code, double amount);
}
