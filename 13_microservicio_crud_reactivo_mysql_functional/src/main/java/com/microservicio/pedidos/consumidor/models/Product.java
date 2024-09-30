package com.microservicio.pedidos.consumidor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.lang.annotation.Annotation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "products")
public class Product implements Persistable<Integer> {

    @Id
    @Column(value = "product_code")
    private int productCode;
    private String name;
    private String category;
    private double amount;
    private Integer stock;
    @Transient  //para no guardarlo en bbdd
    private boolean nuevo;

    @Override
    public Integer getId() {
        return productCode;
    }

    @Override
    public boolean isNew() {
        return nuevo;
    }
}