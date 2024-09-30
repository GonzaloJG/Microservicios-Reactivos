package com.crud.product.controllers;

import com.crud.product.models.Product;
import com.crud.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<Product> products(){
        return productService.catalog();
    }

    @GetMapping("/category/{category}")
    public List<Product> productsByCategory(@PathVariable String category){
        return productService.productsByCategory(category);
    }

    @GetMapping("/{code}")
    public Product productByCode(@PathVariable int code){
        return productService.productByCode(code);
    }

    @PostMapping()
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @DeleteMapping(value = "/{code}")
    public Product deleteProduct(@PathVariable int code){
        return productService.deleteProduct(code);
    }

    @PutMapping
    public Product updateProduct(@RequestParam int code, @RequestParam double amount){
        return productService.updateProduct(code, amount);
    }
}
