package com.nicolas.todo_api.controller;

import com.nicolas.todo_api.model.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {

    @GetMapping("/hello")
    public Produto hello() {
        return new Produto(
                "Notebook Samsung",
                2999.99,
                "Disponível",
                "https://www.mercadolivre.com.br"
        );
    }
}