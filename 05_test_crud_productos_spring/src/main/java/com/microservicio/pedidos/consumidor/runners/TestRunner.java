package com.microservicio.pedidos.consumidor.runners;

import com.microservicio.pedidos.consumidor.models.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class TestRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        WebClient client = WebClient.create("http://localhost:8080");

        /*Flux<Product> flux = client
                .get()
                .uri("/products")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class);

        flux.subscribe(System.out::println);*/

        /*client
                .post()
                .uri("/products")
                .body(Mono.just(new Product(200, "name", "category", 10, 2)), Product.class)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnTerminate(() -> System.out.println("Se ha dado de alta el producto, o no"))
                .block();*/

       /*Mono<Product> monoFind = client
                .get()
                .uri("/products/300")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class);

            monoFind.subscribe(System.out::println);
            monoFind.switchIfEmpty(Mono.just(new Product()).map(p -> {
                System.out.println("No se ha encontrado el producto");
                return p;
            })).block();*/

        client
                .delete()
                .uri("/products/300")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(h -> h.is4xxClientError(), t -> {
                    System.out.println("No se encontró el producto");
                    return Mono.empty();
                })
                .bodyToMono(Product.class)
                .subscribe(System.out::println);
    }
}
