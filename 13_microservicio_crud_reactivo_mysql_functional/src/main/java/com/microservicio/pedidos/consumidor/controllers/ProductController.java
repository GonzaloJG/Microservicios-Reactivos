package com.microservicio.pedidos.consumidor.controllers;

import com.microservicio.pedidos.consumidor.models.Product;
import com.microservicio.pedidos.consumidor.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.*;

//@RestController
@Configuration
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /*@GetMapping
    public Flux<Product> products(){
        return productService.catalog();
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<Flux<Product>> productsByCategory(@PathVariable String category){
        return new ResponseEntity<>(productService.productsByCategory(category), HttpStatus.OK);
    }

    @GetMapping(value = "/{code}")
    public ResponseEntity<Mono<Product>> productsByCode(@PathVariable int code){
        return new ResponseEntity<>(productService.productByCode(code), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Mono<Void>> addProduct(@RequestBody Product product){
        product.setNuevo(true);
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{code}")
    public Mono<ResponseEntity<Product>> deleteProduct(@PathVariable int code) {
        return productService.deleteProduct(code)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping()
    public Mono<ResponseEntity<Product>> updateProduct(@RequestParam int code, @RequestParam double amount){
        return productService.updateProduct(code, amount)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }*/

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(
                RequestPredicates.GET("/products"),
                request -> ServerResponse.ok() //BodyBuilder
                        .body(productService.catalog(), Product.class) //Mono<ServerResponse>
        ) //RouterFunctions<ServerResponse>
                .andRoute(
                        RequestPredicates.GET("/products/category/{category}"),
                        request -> ServerResponse.ok()
                                .body(productService.productsByCategory(request.pathVariable("category")), Product.class)
                )//RouterFunctions<ServerResponse>
                .andRoute(
                        RequestPredicates.GET("/products/{code}"),
                        request -> ServerResponse.ok()
                                .body(productService.productByCode(Integer.parseInt(request.pathVariable("code"))), Product.class)
                )//RouterFunctions<ServerResponse>
                .andRoute(RequestPredicates.POST("/products"),
                        request -> request.bodyToMono(Product.class) //Mono<Product>
                                .flatMap(p -> {
                                    p.setNuevo(true);
                                    return productService.addProduct(p);
                                })// Mono<Void>
                                .flatMap(v -> ServerResponse.ok() //BodyBuilder
                                        .build()) //Mono<ServerResponse>
                )//RouterFunctions<ServerResponse>
                .andRoute(RequestPredicates.DELETE("/products/{code}"),
                        request -> productService.deleteProduct(Integer.parseInt(request.pathVariable("code"))) //Mono<Product>
                                .flatMap(p -> ServerResponse.ok()//BodyBuilder
                                        .bodyValue(p)) //Mono<ServerResponse>
                                .switchIfEmpty(ServerResponse.notFound()//BodyBuilder
                                        .build())//Mono<ServerResponse>
                )//RouterFunctions<ServerResponse>
                .andRoute(RequestPredicates.PUT("/products"),
                        request -> productService.updateProduct(
                                request.queryParam("code").map(Integer::parseInt).get(),
                                request.queryParam("amount").map(Double::parseDouble).get()) //Mono<Product>
                                .flatMap(p -> ServerResponse.ok()//BodyBuilder
                                                .bodyValue(p)) //Mono<ServerReponse>
                                .switchIfEmpty(ServerResponse.notFound()//BodyBuilder
                                        .build())//Mono<ServerReponse>
                );
    }

    @Bean
    public CorsWebFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}
