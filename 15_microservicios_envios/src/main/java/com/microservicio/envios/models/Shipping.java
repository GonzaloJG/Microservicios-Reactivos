package com.microservicio.envios.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "Shipping")
public class Shipping {
    @Id
    private Integer id;
    @JsonAlias(value = "name")
    private String product;
    private LocalDateTime date;
    private String address;
    private String status;
}
