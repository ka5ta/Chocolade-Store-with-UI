package com.gridu.store.service;

import com.gridu.store.model.Product;
import com.gridu.store.model.Stock;
import com.gridu.store.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public boolean subtractQuantity(Product product, int quantityToSubtract) {
        Stock stock = product.getStock();
        int originalQuantity = stock.getQuantity();

        if (originalQuantity >= quantityToSubtract) {
            stock.setQuantity(originalQuantity - quantityToSubtract);
            return true;
        } else {
            return false;
        }

    }
}
