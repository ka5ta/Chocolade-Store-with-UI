package com.gridu.store.controller;

import com.gridu.store.DTO.BasketProductPostDTO;
import com.gridu.store.model.BasketProduct;

import com.gridu.store.repository.BasketProductRepository;
import com.gridu.store.service.BasketProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@RequiredArgsConstructor
public class BasketProductController {

    private final BasketProductService service;

    @Transactional
    @PostMapping("/shopping")
    public String saveOrUpdate(@ModelAttribute BasketProductPostDTO basketProductPostDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("some errors in binding" + bindingResult);
        }

        try {
            BasketProduct basketProduct = service.createOrUpdateBasketProduct(basketProductPostDTO);
            service.save(basketProduct);
        } catch (RuntimeException e) {
            //todo display information to frontend that basket is null or 0 quantity.
            System.out.println();
        }

        return "redirect:shopping";
    }
}
