package com.gridu.store.repository;

import com.gridu.store.DTO.BasketProductDTO;
import com.gridu.store.DTO.BasketProductGetDTO;
import com.gridu.store.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();


}
