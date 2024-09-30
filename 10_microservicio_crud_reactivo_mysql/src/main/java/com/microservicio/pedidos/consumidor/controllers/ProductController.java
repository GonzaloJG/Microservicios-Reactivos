package com.microservicio.pedidos.consumidor.controllers;

import com.microservicio.pedidos.consumidor.models.Product;
import com.microservicio.pedidos.consumidor.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
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
    }
}
