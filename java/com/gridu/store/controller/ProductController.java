package com.gridu.store.controller;

import com.gridu.store.model.Product;
import com.gridu.store.repository.ProductRepository;
import com.gridu.store.service.ProductService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service ) {
        this.service = service;

    }

    @GetMapping
    public List<Product> all() {
        return service.findAll();
    }


    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}/delete")
    public Map<String, Boolean> delete(@PathVariable Long id) {
        Product product = get(id);
        service.delete(product);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

