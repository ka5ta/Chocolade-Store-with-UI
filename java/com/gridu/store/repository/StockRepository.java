package com.gridu.store.repository;

import com.gridu.store.model.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {
}
