package com.gridu.store.service;

import com.gridu.store.DTO.BasketProductDTO;
import com.gridu.store.model.Product;
import com.gridu.store.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductService {

    private final String productIdKey = "product_id";
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public List<BasketProductDTO> getAllProductsInProductBasketForUser(Long accountID){
/*        List<BasketProductDTO> allWithBasketProductByAccountId = repository.findAllWithBasketProductByAccountId(accountID);
        System.out.println(allWithBasketProductByAccountId);
        allWithBasketProductByAccountId.forEach(b -> {
            System.out.println("Product id: "+ b.getBasketProductId());
            System.out.println("BasketQuantity: " + b.getBasketQuantity());
            System.out.println("Basket product id: " + b.getBasketProductId());
            System.out.println("b.getStockQuantity() = " + b.getStockQuantity());
        });*/
        return repository.findAllWithBasketProductByAccountId(accountID);
    }

    public Product findById(long productId){
      return repository.findById(productId).orElseThrow();
    }


    public Product extractProduct(Map<String, String> pairs) {
        long productId;
        String productIdValue = pairs.get(productIdKey);

        if (Objects.nonNull(productIdValue)) {
            productId = Long.parseLong(productIdValue);
        } else throw new RuntimeException("Product ID is null");

        return repository.findById(productId).orElseThrow(() -> new RuntimeException("There is no such product with id: " +
                productId + " in database."));

    }



}
