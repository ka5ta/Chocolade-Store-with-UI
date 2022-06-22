package com.gridu.store.service;

import com.gridu.store.model.BasketProduct;
import com.gridu.store.model.Product;
import com.gridu.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;


    public Product findById(long productId){
      return repository.findById(productId).orElseThrow(
              () -> new ResourceNotFoundException("Item not found for id: " + productId)
      );
    }
}
