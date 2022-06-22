package com.gridu.store.repository;

import com.gridu.store.dto.BasketProductSqlDTO;
import com.gridu.store.model.Account;
import com.gridu.store.model.Product;
import com.gridu.store.model.BasketProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BasketProductRepository extends CrudRepository<BasketProduct, Long> {

    Optional<BasketProduct> findByAccountAndProduct(Account account, Product product);

//todo replace with mapper: https://mapstruct.org/ and https://www.baeldung.com/mapstruct
    @Query(value = "SELECT " +
            "p.id as productId, " +
            "s.quantity as stockQuantity, " +
            "b.quantity as basketQuantity, " +
            "b.id as basketProductId " +
            "FROM PRODUCTS p " +
            "LEFT JOIN STOCK s ON s.PRODUCT_ID = p.ID " +
            "LEFT JOIN BASKET_PRODUCTS b ON b.PRODUCT_ID=p.ID and b.ACCOUNT_ID=:accountId", nativeQuery = true)
    List<BasketProductSqlDTO> findAllWithBasketProductByAccountId(@Param("accountId") Long accountId);


    List<BasketProduct> findByAccountId(Long accountId);
}
