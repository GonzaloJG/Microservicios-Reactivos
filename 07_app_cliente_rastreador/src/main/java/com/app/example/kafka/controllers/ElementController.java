package com.app.example.kafka.controllers;

import com.app.example.kafka.services.ElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

@Controller
@RequiredArgsConstructor
public class ElementController {
    public final ElementService elementService;

    @GetMapping(value= "find")
    public String find(@RequestParam double price, Model model) {
        IReactiveDataDriverContextVariable reactive =
                new ReactiveDataDriverContextVariable(elementService.elemetsByPrice(price), 1);
        model.addAttribute("result", reactive);
        return "list";
    }

    @GetMapping("/")
    public String inicio(){
        return "inicio";
    }

}
