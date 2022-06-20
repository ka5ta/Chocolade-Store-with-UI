package com.gridu.store.service;

import com.gridu.store.DTO.BasketProductDTO;
import com.gridu.store.DTO.BasketProductGetDTO;
import com.gridu.store.DTO.BasketProductPostDTO;
import com.gridu.store.model.Account;
import com.gridu.store.model.Product;
import com.gridu.store.model.BasketProduct;
import com.gridu.store.repository.BasketProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service @RequiredArgsConstructor
public class BasketProductService {

    private final BasketProductRepository repository;
    private final ProductService productService;
    private final AccountService accountService;
    private final StockService stockService;

    public List<BasketProductGetDTO> getBasketProductForAccount(Account account) {
        List<BasketProductDTO> allProductsInProductBasketForUser = getAllProductsInProductBasketForUser(account);
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

    public BasketProduct createOrUpdateBasketProduct(BasketProductPostDTO basketProductPostDTO) {

        Product product = productService.findById(basketProductPostDTO.getProductId());
        Account account = accountService.findById(basketProductPostDTO.getAccountId());
        Integer quantity = basketProductPostDTO.getBasketQuantity();

        if (!canUpdateQuantity(product, quantity)) {
            throw new RuntimeException("Quantity is not available");
        }


        BasketProduct basketByAccountAndProduct = repository
                .findByAccountAndProduct(account, product)
                .orElseGet(() -> {

                            if (quantity == 0 || quantity == null) {
                                throw new RuntimeException("Basket with 0 quantity can't be created");
                            }

                            BasketProduct newBasketProduct = new BasketProduct();
                            newBasketProduct.setProduct(product);
                            newBasketProduct.setAccount(account);
                            return newBasketProduct;
                        }
                );

        basketByAccountAndProduct.setQuantity(quantity);

        return basketByAccountAndProduct;
    }

    private boolean canUpdateQuantity(Product product, int quantity) {
        int currentStockAmount = stockService.getStockForProduct(product);
        return currentStockAmount >= quantity;
    }

    public void save(BasketProduct basketProduct) {
        repository.save(basketProduct);
    }


    public List<BasketProductDTO> getAllProductsInProductBasketForUser(Account account) {
        return repository.findAllWithBasketProductByAccountId(account.getId());
    }

    public List<BasketProduct> findAllByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }

    public void remove(BasketProduct basketProduct) {
        repository.delete(basketProduct);
    }
}

