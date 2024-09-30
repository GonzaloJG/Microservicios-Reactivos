package com.microservicio.pedidos.consumidor;

import com.microservicio.pedidos.consumidor.models.Product;
import com.microservicio.pedidos.consumidor.services.ProductService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@TestMethodOrder(MethodOrderer.class)
@SpringBootTest
class ApplicationTests {

    @Autowired
    private ProductService service;

    @Test
    @Order(1)
    void testProductByCategiory() {
        StepVerifier.create(service.productsByCategory("Alimentación"))
                .expectNextMatches(p -> p.getName().equals("Azucar"))
                .expectNextMatches(p -> p.getName().equals("Leche"))
                .expectNextMatches(p -> p.getName().equals("Huevos"))
                .verifyComplete();
    }

     @Test
     @Order(2)
    void testDeleteProduct() {
        StepVerifier.create(service.deleteProduct(103))
                .expectNextMatches(p -> p.getName().equals("Mesa"))
                .verifyComplete();

     }

     @Test
     @Order(4)
    void testAddProduct() {
         Product product = new Product(250, "test", "cat1", 10, 2);
        StepVerifier.create(service.addProduct(product))
                .expectComplete()
                .verify();
     }

     @Test
     @Order(3)
    void testCatalog(){
        StepVerifier.create(service.catalog())
                .expectNextCount(8)
                .verifyComplete();
     }

}
