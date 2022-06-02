package com.gridu.store.controller;

import com.gridu.store.DTO.BasketProductPostDTO;
import com.gridu.store.repository.BasketProductRepository;
import com.gridu.store.repository.StockRepository;
import com.gridu.store.repository.ProductRepository;
import com.gridu.store.service.BasketProductService;
import com.gridu.store.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shopping")
public class WebController {

    private final ProductService productService;
    private final BasketProductService basketProductService;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    private final BasketProductRepository basketProductRepository;


    public WebController(ProductService productService, BasketProductService basketProductService, ProductRepository productRepository, StockRepository stockRepository, BasketProductRepository basketProductRepository) {
        this.productService = productService;
        this.basketProductService = basketProductService;
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.basketProductRepository = basketProductRepository;
    }


    @GetMapping
    public String shopping(ModelMap modelMap){
        modelMap.addAttribute("productBaskets", basketProductService.getBasketProductForAccount(1L));
        modelMap.addAttribute("basketProductPostDTO", new BasketProductPostDTO());
        return "shopping";
    }

    @GetMapping(value = "/register")
    public ModelAndView register(Model model){
        return new ModelAndView("register");
    }

    @GetMapping(value = "/signin")
    public String signin(Model model){
        return "signin";
    }



}


//https://www.baeldung.com/spring-boot-crud-thymeleaf


/*
select
        p.id as product_id,
        p.name as product_name,
        p.price as product_price,
        s.quantity as stock_quantity,
        bp.quantity as basket_quantity,
        bp.account_id as basket_account_id
        from products p
        left join stock s
        on s.product_id = p.id
        left join basket_products bp
        on
        bp.product_id = p.id
        and bp.account_id = 1
        ;*/
