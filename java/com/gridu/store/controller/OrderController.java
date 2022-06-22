package com.gridu.store.controller;

import com.gridu.store.model.Order;
import com.gridu.store.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.security.Principal;


@Controller @RequiredArgsConstructor
@RequestMapping("/shopping/order")
public class OrderController {

    private final OrderService service;


    @Transactional
    @PostMapping()
    public String create(Principal principal){
        Order order = service.createOrder(principal.getName());
        service.save(order);

        return "order";
    }

}
