package com.microservicio.pedidos.consumidor.repositories;

import com.microservicio.pedidos.consumidor.models.Product;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, Integer> {
    Flux<Product> findByCategory(String category);

    @DeleteQuery(value = "{'amount': {$lt: ?0}}")
    Mono<Void> deletePrice(double price);
}
