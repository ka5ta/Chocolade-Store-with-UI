package com.gridu.store.service;

import com.gridu.store.DTO.BasketProductDTO;
import com.gridu.store.DTO.BasketProductGetDTO;
import com.gridu.store.model.Product;
import com.gridu.store.repository.ProductRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductService {


    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return new ArrayList<>(repository.findAll());
    }


    public Product findById(long productId){
      return repository.findById(productId).orElseThrow(
              () -> new ResourceNotFoundException("Item not found for id: " + productId)
      );
    }


    public void delete(Product product) {
        repository.delete(product);
    }
}
