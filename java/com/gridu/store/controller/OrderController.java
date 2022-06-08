package com.gridu.store.controller;

import com.gridu.store.model.BasketProduct;
import com.gridu.store.model.Order;
import com.gridu.store.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shopping/order")
public class OrderController {

    private final OrderService service;
    private final OrderProductService orderProductService;
    private final ProductService productService;
    private final BasketProductService basketProductService;


    public OrderController(OrderService service, OrderProductService orderProductService, ProductService productService, BasketProductService basketProductService, AccountService accountService) {
        this.service = service;
        this.orderProductService = orderProductService;
        this.productService = productService;
        this.basketProductService = basketProductService;

    }


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
