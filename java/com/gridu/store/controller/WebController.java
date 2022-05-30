package com.gridu.store.controller;

import com.gridu.store.repository.StoreRepository;
import com.gridu.store.service.StoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Controller
@RequestMapping("shopping")
public class WebController {

    private final StoreService service;
    private final StoreRepository repository;

    public WebController(StoreService service, StoreRepository repository) {
        this.service = service;
        this.repository = repository;
    }


    @GetMapping
    public String shopping(Model model){
        model.addAttribute("items", service.findAll());
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