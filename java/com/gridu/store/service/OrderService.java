package com.gridu.store.service;

import com.gridu.store.model.Account;
import com.gridu.store.model.BasketProduct;
import com.gridu.store.model.Order;
import com.gridu.store.model.OrderProduct;
import com.gridu.store.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final AccountService accountService;
    private final BasketProductService basketProductService;
    private final OrderProductService orderProductService;


    public Order createOrder(String email) {
        Account account = accountService.findByEmailOrThrow(email);
        Order order = new Order(account);

        List<BasketProduct> basketProducts = basketProductService.findAllByAccountId(account.getId());
        List<OrderProduct> orderProducts = orderProductService.convertBasketProductsToOrderProducts(basketProducts, order);
        order.setOrderProducts(orderProducts);

        basketProductService.clearAllBasketsForAccountAndAdjustStock(account);

        return order;
    }


    public void save(Order order) {
        repository.save(order);
    }
}
