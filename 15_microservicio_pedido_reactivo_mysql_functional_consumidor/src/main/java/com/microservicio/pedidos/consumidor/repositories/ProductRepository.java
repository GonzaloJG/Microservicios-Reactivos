package com.microservicio.pedidos.consumidor.repositories;

import com.microservicio.pedidos.consumidor.models.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
    Flux<Product> findByCategory(String category);
}
