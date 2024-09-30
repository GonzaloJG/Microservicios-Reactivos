package com.crud.product.services;

import com.crud.product.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static List<Product> products =
            new ArrayList<>(List.of(
                    new Product(100,"Azucar","Alimentación",1.10,20),
                    new Product(101,"Leche","Alimentación",1.20,15),
                    new Product(102,"Jabón","Limpieza",0.89,30),
                    new Product(103,"Mesa","Hogar",125,4),
                    new Product(104,"Televisión","Hogar",650,10),
                    new Product(105,"Huevos","Alimentación",2.20,30),
                    new Product(106,"Fregona","Limpieza",3.40,6),
                    new Product(107,"Detergente","Limpieza",8.7,12)));

    @Override
    public List<Product> catalog() {
        return products;
    }

    @Override
    public List<Product> productsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equals(category))
                .toList();
    }

    @Override
    public Product productByCode(int code) {
        return products.stream()
                .filter(p -> p.getProductCode() == code)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        if (productByCode(product.getProductCode()) == null) {
            products.add(product);
        }

    }

    @Override
    public Product deleteProduct(int code) {
        Product product = productByCode(code);
        if (product != null) {
            products.removeIf(p -> p.getProductCode() == code);
        }
        return product;
    }

    @Override
    public Product updateProduct(int code, double amount) {
        Product product = productByCode(code);
        if (product != null) {
            product.setAmount(amount);
        }
        return product;
    }
}
