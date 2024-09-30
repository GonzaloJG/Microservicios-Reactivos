package com.microservicio.pedidos.productor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer productCode;
    private String name;
    private Integer units;
    private String address;
}
