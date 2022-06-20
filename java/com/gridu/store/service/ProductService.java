package com.gridu.store.service;

import com.gridu.store.DTO.BasketProductDTO;
import com.gridu.store.DTO.BasketProductGetDTO;
import com.gridu.store.model.BasketProduct;
import com.gridu.store.model.Product;
import com.gridu.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service @RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;


    public List<Product> findAll() {
        return new ArrayList<>(repository.findAll());
    }


    public Product findById(long productId){
      return repository.findById(productId).orElseThrow(
              () -> new ResourceNotFoundException("Item not found for id: " + productId)
      );
    }

    public void subtractQuantityFromStock(BasketProduct basketProduct){
        Product product = basketProduct.getProduct();
        int productQuantity = product.getStock().getQuantity();
        int userQuantity = basketProduct.getQuantity();

        int subtracted = productQuantity - userQuantity;
        product.getStock().setQuantity(subtracted);

    }

    public void delete(Product product) {
        repository.delete(product);
    }
}
