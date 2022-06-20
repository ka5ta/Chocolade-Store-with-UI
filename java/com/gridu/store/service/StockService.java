package com.gridu.store.service;

import com.gridu.store.model.Product;
import com.gridu.store.model.Stock;
import com.gridu.store.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class StockService {

    private final StockRepository repository;

    public int getStockForProduct(Product product) {
        return product.getStock().getQuantity();
    }

}
