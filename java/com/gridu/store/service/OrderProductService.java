package com.gridu.store.service;


import com.gridu.store.model.BasketProduct;
import com.gridu.store.model.Order;
import com.gridu.store.model.OrderProduct;
import com.gridu.store.model.Product;
import com.gridu.store.repository.OrderProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderProductService {

    private final OrderProductRepository repository;


    public OrderProductService(OrderProductRepository repository ){
        this.repository = repository;

    }

    public OrderProduct createOrderProduct(BasketProduct basketProduct, Order order) {
        Product product = basketProduct.getProduct();
        int quantity = basketProduct.getQuantity();

        return new OrderProduct(product,quantity, order);
    }



    public List<OrderProduct> convertBasketProductsToOrderProducts(List<BasketProduct> basketProducts, Order order){
        List<OrderProduct> orderProducts = new ArrayList<>();

        for (BasketProduct basketProduct : basketProducts) {
            OrderProduct orderProduct = createOrderProduct(basketProduct, order);
            orderProducts.add(orderProduct);
        }
        return orderProducts;
    }

}
