package com.gridu.store.service;

import com.gridu.store.model.Item;
import com.gridu.store.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    private final StoreRepository repository;

    public StoreService(StoreRepository repository) {
        this.repository = repository;
    }

    public List<Item> findAll(){
        return new ArrayList<>(repository.findAll());
    }
}
