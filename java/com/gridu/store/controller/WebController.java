package com.gridu.store.controller;

import com.gridu.store.DTO.BasketProductPostDTO;
import com.gridu.store.DTO.AccountDTO;
import com.gridu.store.service.BasketProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shopping")
public class WebController {


    private final BasketProductService basketProductService;


    public WebController(BasketProductService basketProductService) {
        this.basketProductService = basketProductService;
    }


    @GetMapping
    public String shopping(ModelMap modelMap){
        modelMap.addAttribute("productBaskets", basketProductService.getBasketProductForAccount(1L));
        //todo accountID is currently hardcoded
        modelMap.addAttribute("accountId", 1L);
        modelMap.addAttribute("basketProductPostDTO", new BasketProductPostDTO());
        return "shopping";
    }

    @GetMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("accountDTO", new AccountDTO());
        return "register";
    }

    @GetMapping(value = "/signin")
    public String signin(Model model){
        model.addAttribute("accountDTO", new AccountDTO());
        return "signin";
    }

}
