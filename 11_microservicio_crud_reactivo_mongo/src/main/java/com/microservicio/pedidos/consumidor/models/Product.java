package com.microservicio.pedidos.consumidor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private int id;
    private String name;
    private String category;
    private double amount;
    private Integer stock;
    
}