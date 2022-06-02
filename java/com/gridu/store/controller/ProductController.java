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
    private final ProductRepository repository;

    public ProductController(ProductService service, ProductRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Product> all() {
        return service.findAll();
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        repository.save(product);
        return product;
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Item not found for id: " + id)
        );
    }

    @DeleteMapping("/{id}/delete")
    public Map<String, Boolean> delete(@PathVariable Long id) {
        Product product = get(id);
        repository.delete(product);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

