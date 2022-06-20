package com.gridu.store.controller;

import com.gridu.store.model.BasketProduct;
import com.gridu.store.model.Order;
import com.gridu.store.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;

@Controller @RequiredArgsConstructor
@RequestMapping("/shopping/order")
public class OrderController {

    private final OrderService service;
    private final ProductService productService;
    private final BasketProductService basketProductService;



    @Transactional
    @PostMapping()
    public String create(Long accountId){
        Order order = service.createOrder(accountId);
        service.save(order);

        List<BasketProduct> basketProducts = basketProductService.findAllByAccountId(accountId);
        basketProducts.forEach(bp -> {
            productService.subtractQuantityFromStock(bp);
            basketProductService.remove(bp);
        });

        return "order";
    }

}
