package com.rastreador.reactivo.services;

import com.rastreador.reactivo.models.Element;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class ElementServiceImpl implements ElementService {
    String url1 = "http://localhost:8080";
    String url2 = "http://localhost:8090";

    @Override
    public Flux<Element> findByPriceMax(double priceMax) {
        Flux<Element> flux1 = catalog(url1, "Shop 1");
        Flux<Element> flux2 = catalog(url2, "Shop 2");

        return Flux.merge(flux1, flux2)
                .filter(e -> e.getAmount() <= priceMax);
    }

    private Flux<Element> catalog(String url, String shop){
        WebClient client = WebClient.create(url);

        return client
                .get()
                .uri("/products")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Element.class)
                .map(e -> {
                    e.setShop(shop);
                    return e;
                });
    }
}
