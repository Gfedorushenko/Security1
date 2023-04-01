package ru.netology.security1.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.security1.repository.RepositoryPostgres;

import javax.annotation.security.RolesAllowed;
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

    @Secured({"ROLE_READ"})
    @GetMapping("/products/read")
    public String getRead() {
        return "I can read";
    }

    @RolesAllowed({"ROLE_WRITE"})
    @GetMapping("/products/write")
    public String getWrite() {
        return "I can read";
    }

    @PreAuthorize("hasAnyRole('WRITE')||hasAuthority('WRITE')")
    @GetMapping("/products/add")
    public String getRole() {
        return "I can!!!";
    }


}