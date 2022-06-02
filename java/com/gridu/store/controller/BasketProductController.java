package com.gridu.store.controller;

import com.gridu.store.DTO.BasketProductPostDTO;
import com.gridu.store.model.BasketProduct;

import com.gridu.store.repository.BasketProductRepository;
import com.gridu.store.service.BasketProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class BasketProductController {


    private final BasketProductRepository repository;
    private final BasketProductService service;

    public BasketProductController(BasketProductRepository repository, BasketProductService service) {
        this.repository = repository;
        this.service = service;
    }


    @PostMapping("/shopping")
    public String saveOrUpdate(@ModelAttribute BasketProductPostDTO basketProductPostDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            System.out.println("some errors in binding" + bindingResult);
        }

        BasketProduct newBasketProduct = service.createBasketProduct(basketProductPostDTO);
        repository.save(newBasketProduct);
    return "redirect:shopping";
    }
}
