package com.gridu.store.controller;

import com.gridu.store.dto.BasketProductPostDTO;
import com.gridu.store.dto.PasswordChangeDTO;
import com.gridu.store.dto.RegisterAccountDTO;
import com.gridu.store.dto.SigninDTO;
import com.gridu.store.model.Account;
import com.gridu.store.service.AccountService;
import com.gridu.store.service.BasketProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller @RequiredArgsConstructor
@RequestMapping("/shopping")
public class WebController {


    private final BasketProductService basketProductService;
    private final AccountService accountService;

    @GetMapping
    public String shopping(ModelMap modelMap, Principal principal) {
        Account account = accountService.findByEmailOrThrow(principal.getName());

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
        model.addAttribute("registerAccountDTO", new RegisterAccountDTO());
        return "register";
    }

    @GetMapping(value = "/signin")
    public String signin(Model model) {
        model.addAttribute("signinDTO", new SigninDTO());
        return "signin";
    }

    @GetMapping("/password")
    public String changePassword(Model model) {
        model.addAttribute("passwordChangeDTO", new PasswordChangeDTO());
        return "password";
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

    @GetMapping("/signin/success")
    public String successSignin() {
        return "success";
    }

    @GetMapping("/signin/failure")
    public String signinFailure(Model model) {
        model.addAttribute("signinFailure", "Your email or password are wrong. Try again to sing in or register. ");
        return "failure";
    }

    @GetMapping("/register/success")
    public String successRegister() {
        return "success";
    }

    @GetMapping("/register/failure")
    public String failureRegister() {
        return "failure";
    }


    @GetMapping("/password/success")
    public String changePasswordSuccess() {
        return "success";
    }

    @GetMapping("/password/failure")
    public String changePasswordFailure() {
        return "failure";
    }

}
