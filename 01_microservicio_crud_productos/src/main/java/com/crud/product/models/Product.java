package com.crud.product.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private int productCode;
    private String name;
    private String category;
    private double amount;
    private Integer stock;
}
