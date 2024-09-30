package com.crud.product.services;

import com.crud.product.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> catalog();
    List<Product> productsByCategory(String category);
    Product productByCode(int code);
    void addProduct(Product product);
    Product deleteProduct(int code);
    Product updateProduct(int code, double amount);
}
