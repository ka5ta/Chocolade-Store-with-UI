package com.gridu.store.controller;

import com.gridu.store.dto.BasketProductPostDTO;
import com.gridu.store.model.BasketProduct;

import com.gridu.store.service.BasketProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BasketProductController {

    private final BasketProductService service;

    @Transactional
    @PostMapping("/shopping")
    public String saveOrUpdate(@ModelAttribute BasketProductPostDTO basketProductPostDTO, Principal principal) {

        try {
            BasketProduct basketProduct = service.createOrUpdateBasketProduct(basketProductPostDTO, principal.getName());
            service.save(basketProduct);
        } catch (RuntimeException e) {
            //todo display information to frontend that basket is null or 0 quantity.
            System.out.println();
        }

        return "redirect:shopping";
    }
}
