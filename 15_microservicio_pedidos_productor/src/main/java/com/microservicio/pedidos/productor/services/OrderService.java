package com.microservicio.pedidos.productor.services;

import com.microservicio.pedidos.productor.models.Order;

public interface OrderService {
    void orderRegister(Order order);
}
