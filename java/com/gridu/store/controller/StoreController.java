package com.gridu.store.controller;

import com.gridu.store.model.Item;
import com.gridu.store.repository.StoreRepository;
import com.gridu.store.service.StoreService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("products")
public class StoreController {

    private final StoreService service;
    private final StoreRepository repository;

    public StoreController(StoreService service, StoreRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Item> all() {
        return service.findAll();
    }

    @PostMapping
    public Item add(@RequestBody Item item) {
        repository.save(item);
        return item;
    }

    @GetMapping("/{id}")
    public Item get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Item not found for id: " + id)
        );
    }

    @DeleteMapping("/{id}/delete")
    public Map<String, Boolean> delete(@PathVariable Long id) {
        Item item = get(id);
        repository.delete(item);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

