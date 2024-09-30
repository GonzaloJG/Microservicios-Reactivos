package com.microservicio.pedidos.consumidor.services;

import com.microservicio.pedidos.consumidor.models.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static List<Product> products = new ArrayList<>(List.of(
                    new Product(110,"Sal","Alimentación",1.10,20),
                    new Product(111,"Pan","Alimentación",1.30,15),
                    new Product(112,"Esponja","Limpieza",2,20),
                    new Product(113,"Sofá","Hogar",80,4),
                    new Product(114,"Jarrón","Hogar",40,10),
                    new Product(115,"Harina","Alimentación",3,30),
                    new Product(116,"Recogedor","Limpieza",3.40,6),
                    new Product(117,"Cubo","Limpieza",8.7,12)));

    @Override
    public Flux<Product> catalog() {
        return Flux.fromIterable(products)
                .delayElements(Duration.ofMillis(500));
    }

    @Override
    public Flux<Product> productsByCategory(String category) {
        return Flux.fromIterable(products)
                .filter(product -> product.getCategory().equals(category));
    }

    @Override
    public Mono<Product> productByCode(int code) {
        return Flux.fromIterable(products)
                .filter(product -> product.getProductCode() == code)
                .next();
                //.switchIfEmpty(Mono.just(new Product()));
    }

    @Override
    public Mono<Void> addProduct(Product product) {
        return productByCode(product.getProductCode())
                .switchIfEmpty(
                        Mono.just(product)
                        .map( p -> {
                            products.add(p);
                            return p;
                        })) //Mono<Producto>
                .then(); //Mono<Void>
    }

    @Override
    public Mono<Product> deleteProduct(int code) {
        return productByCode(code)
                .map(product -> {
                    products.removeIf(p -> p.getProductCode() == code);
                    return product;
                });
    }

    @Override
    public Mono<Product> updateProduct(int code, double amount) {
        return productByCode(code)
                .map(product -> {
                    product.setAmount(amount);
                    return product;
                });
    }
}
