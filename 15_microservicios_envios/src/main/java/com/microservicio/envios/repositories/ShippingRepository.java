package com.microservicio.envios.repositories;

import com.microservicio.envios.models.Shipping;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ShippingRepository extends ReactiveCrudRepository<Shipping, Integer> {
    @Query(value = "SELECT * FROM Shipping s where s.status=:status")
    Flux<Shipping> findByStatus(String status);
}
