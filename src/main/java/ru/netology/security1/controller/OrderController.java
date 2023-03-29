package ru.netology.security1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.security1.repository.RepositoryPostgres;

import java.util.List;

@RestController
public class OrderController {
    private RepositoryPostgres repositoryPostgres;

    public OrderController(RepositoryPostgres repositoryPostgres) {
        this.repositoryPostgres = repositoryPostgres;
    }

    @GetMapping("/products")
    public List<String> getAuthorities() {
        return repositoryPostgres.getProducts();
    }

    @GetMapping("/products/fetch-product")
    public List<String> getAuthorities(@RequestParam(name = "name") String name) {
        return repositoryPostgres.getProductName(name);
    }
}