package com.rastreador.reactivo.services;

import com.rastreador.reactivo.models.Element;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ElementServiceImpl implements ElementService {
    String url1 = "http://servicio-productos1";
    String url2 = "http://servicio-productos2";

    private final WebClient.Builder builder;

    @Override
    public Flux<Element> findByPriceMax(double priceMax) {
        Flux<Element> flux1 = catalog(url1, "Shop 1");
        Flux<Element> flux2 = catalog(url2, "Shop 2");

        return Flux.merge(flux1, flux2)
                .filter(e -> e.getAmount() <= priceMax);
    }

    private Flux<Element> catalog(String url, String shop){
        WebClient client = builder.build();

        return client
                .get()
                .uri(url + "/products")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Element.class)
                .map(e -> {
                    e.setShop(shop);
                    return e;
                });
    }
}
