package com.gridu.store.service;

import com.gridu.store.model.Account;
import com.gridu.store.model.BasketProduct;
import com.gridu.store.model.Order;
import com.gridu.store.model.OrderProduct;
import com.gridu.store.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final AccountService accountService;
    private final BasketProductService basketProductService;
    private final OrderProductService orderProductService;


    public OrderService(OrderRepository repository, AccountService accountService, BasketProductService basketProductService, OrderProductService orderProductService) {
        this.repository = repository;
        this.accountService = accountService;
        this.basketProductService = basketProductService;
        this.orderProductService = orderProductService;
    }

    @Transactional
    public Order createOrder(long accountId) {
        Account account = accountService.findById(accountId);
        Order order = new Order(account);

        List<BasketProduct> basketProducts = basketProductService.findAllByAccountId(accountId);
        List<OrderProduct> orderProducts = orderProductService.convertBasketProductsToOrderProducts(basketProducts, order);
        order.setOrderProducts(orderProducts);

        return order;
    }

    public void save(Order order) {
        repository.save(order);
    }
}
