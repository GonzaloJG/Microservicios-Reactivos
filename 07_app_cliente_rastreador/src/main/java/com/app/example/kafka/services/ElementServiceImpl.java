package com.app.example.kafka.services;

import com.app.example.kafka.models.Element;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ElementServiceImpl implements ElementService {
    String url = "http://localhost:8081";

    private final WebClient webClient;

    @Override
    public Flux<Element> elemetsByPrice(double priceMax) {
        //WebClient webClient = WebClient.create(url);

        return webClient
                .get()
                .uri(url + "/elements/" + priceMax)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Element.class);
    }
}
