package com.gridu.store.service;

import com.gridu.store.DTO.BasketProductDTO;
import com.gridu.store.DTO.BasketProductGetDTO;
import com.gridu.store.DTO.BasketProductPostDTO;
import com.gridu.store.model.Account;
import com.gridu.store.model.Product;
import com.gridu.store.model.BasketProduct;
import com.gridu.store.repository.BasketProductRepository;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasketProductService {

    private final BasketProductRepository repository;
    private final ProductService productService;
    private final AccountService accountService;
    private final StockService stockService;


    public BasketProductService(BasketProductRepository repository, ProductService productService, AccountService accountService, StockService stockService) {
        this.repository = repository;
        this.productService = productService;
        this.accountService = accountService;
        this.stockService = stockService;
    }

    public List<BasketProductGetDTO> getBasketProductForAccount(Long accountId) {
        List<BasketProductDTO> allProductsInProductBasketForUser = productService.getAllProductsInProductBasketForUser(accountId);
        List<BasketProductGetDTO> basketsProductForAccount = new ArrayList<>();

        allProductsInProductBasketForUser.forEach(b -> {

            Product product = productService.findById(b.getProductId());
            Integer stockQuantity = b.getStockQuantity();
            Long basketProductId = b.getBasketProductId();
            Integer basketQuantity = b.getBasketQuantity();

            BasketProductGetDTO basketProductGetDTO = new BasketProductGetDTO();
            basketProductGetDTO.setProductId(product.getId());
            basketProductGetDTO.setProductName(product.getName());
            basketProductGetDTO.setProductPrice(product.getPrice());
            basketProductGetDTO.setStockQuantity(stockQuantity);
            basketProductGetDTO.setBasketProductId(basketProductId);
            basketProductGetDTO.setBasketQuantity(basketQuantity);
            basketsProductForAccount.add(basketProductGetDTO);
        });

        return basketsProductForAccount;
    }

    public BasketProduct createBasketProduct(BasketProductPostDTO basketProductPostDTO) {

        Product product = productService.findById(basketProductPostDTO.getProductId());
        Account account = accountService.findById(basketProductPostDTO.getAccountId());
        int quantity = basketProductPostDTO.getBasketQuantity();

        if (!canUpdateQuantity(product, quantity)) {
            throw new RuntimeException("Quantity is not available");
        }

        BasketProduct basketByAccountAndProduct = repository
                .findByAccountAndProduct(account, product)
                .orElseGet( () -> {
                    BasketProduct newBasketProduct = new BasketProduct();
                    newBasketProduct.setProduct(product);
                    newBasketProduct.setAccount(account);
                    return newBasketProduct;}
                );

        basketByAccountAndProduct.setQuantity(quantity);

        return basketByAccountAndProduct;
    }

    private boolean canUpdateQuantity(Product product, int quantity) {
        return stockService.subtractQuantity(product, quantity);
    }
}