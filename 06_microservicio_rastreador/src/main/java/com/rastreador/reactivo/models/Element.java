package com.rastreador.reactivo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Element {

    private String name;
    private String category;
    private Double amount;
    private String shop;

}
