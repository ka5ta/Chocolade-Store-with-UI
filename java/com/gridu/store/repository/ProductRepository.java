package com.gridu.store.repository;

import com.gridu.store.DTO.BasketProductDTO;
import com.gridu.store.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    @Query(value = "SELECT " +
            "p.id as productId, " +
            "s.quantity as stockQuantity, " +
            "b.quantity as basketQuantity, " +
            "b.id as basketProductId " +
            "FROM PRODUCTS p " +
            "LEFT JOIN STOCK s ON s.PRODUCT_ID = p.ID " +
            "LEFT JOIN BASKET_PRODUCTS b ON b.PRODUCT_ID=p.ID and b.ACCOUNT_ID=:accountId", nativeQuery = true)
            List<BasketProductDTO> findAllWithBasketProductByAccountId(@Param("accountId") Long accountId);


}
