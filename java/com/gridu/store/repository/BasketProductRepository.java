package com.gridu.store.repository;

import com.gridu.store.model.Account;
import com.gridu.store.model.Product;
import com.gridu.store.model.BasketProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BasketProductRepository extends CrudRepository<BasketProduct, Long> {

    Optional<BasketProduct> findByAccountAndProduct(Account account, Product product);
}
