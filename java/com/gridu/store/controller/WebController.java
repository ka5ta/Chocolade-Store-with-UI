package com.gridu.store.controller;

import com.gridu.store.DTO.BasketProductGetDTO;
import com.gridu.store.DTO.BasketProductPostDTO;
import com.gridu.store.DTO.AccountDTO;
import com.gridu.store.model.Account;
import com.gridu.store.service.AccountService;
import com.gridu.store.service.BasketProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller @RequiredArgsConstructor
@RequestMapping("/shopping")
public class WebController {


    private final BasketProductService basketProductService;
    private final AccountService accountService;

    @GetMapping
    public String shopping(ModelMap modelMap, Principal principal) {
        Account account = accountService.findByEmail(principal.getName());

        modelMap.addAttribute("productBaskets", basketProductService.getBasketProductForAccount(account));
        modelMap.addAttribute("basketProductPostDTO", new BasketProductPostDTO());
        modelMap.addAttribute("email", account.getEmail());
        modelMap.addAttribute("accountId", account.getId());

        // toggle "process payment button"
        if (basketProductService.findAllByAccountId(account.getId()).isEmpty()) {
            modelMap.addAttribute("disabled", true);
        } else {
            modelMap.addAttribute("disabled", false);
        }
        return "shopping";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("accountDTO", new AccountDTO());
        return "register";
    }

    @GetMapping(value = "/signin")
    public String signin(Model model) {
        model.addAttribute("accountDTO", new AccountDTO());
        return "signin";
    }


    // for testing:
    @GetMapping("user")
    public String helloUser() {
        return "user";
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "admin";
    }

    @GetMapping("/logout")
    public String logout() {

        return "logout";
    }

    @GetMapping("/signin/failure")
    public String failure(Model model) {
        model.addAttribute("emailPasswordFailure", "Your email or password are wrong. Try again to sing in or register. ");
        return "failure";
    }

    @GetMapping("/signin/signin-success")
    public String successSignin() {
        return "signin-success";
    }

    @GetMapping("/register/reg-success")
    public String successRegister() {
        return "reg-success";
    }

    @GetMapping("/register/failure")
    public String failureRegister() {
        return "failure";
    }

}
