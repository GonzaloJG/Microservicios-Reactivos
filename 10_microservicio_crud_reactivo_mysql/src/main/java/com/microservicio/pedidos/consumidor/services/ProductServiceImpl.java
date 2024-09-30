package com.microservicio.pedidos.consumidor.services;

import com.microservicio.pedidos.consumidor.models.Product;
import com.microservicio.pedidos.consumidor.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Flux<Product> catalog() {
        return productRepository.findAll() //Flux<Product>
                .delayElements(Duration.ofMillis(500));
    }

    @Override
    public Flux<Product> productsByCategory(String category) {
        return productRepository.findByCategory(category); //Flux<Product>
    }

    @Override
    public Mono<Product> productByCode(int code) {
        return productRepository.findById(code); //Mono<Product>
    }

    @Override
    public Mono<Void> addProduct(Product product) {
        return productByCode(product.getProductCode()) //Mono<Product>
                .switchIfEmpty(
                        Mono.just(product)
                        .flatMap(productRepository::save)
                )//Mono<Producto>
                .then(); //Mono<Void>
    }

    @Override
    public Mono<Product> deleteProduct(int code) {
        return productByCode(code) //Mono<Product>
                .flatMap(p -> productRepository.deleteById(code) //Mono<Void>
                                .then(Mono.just(p)) //Mono<Product>
                );
    }

    @Override
    public Mono<Product> updateProduct(int code, double amount) {
        return productByCode(code) //Mono<Product>
                .flatMap(p -> {
                    p.setAmount(amount);
                    return productRepository.save(p); //Mono<Product>
                });
    }
}
