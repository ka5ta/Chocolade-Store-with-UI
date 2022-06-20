package com.gridu.store.controller;


import com.gridu.store.DTO.AccountDTO;
import com.gridu.store.model.Account;
import com.gridu.store.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.security.auth.login.AccountException;
import java.security.Principal;
import java.util.Optional;

import static com.gridu.store.constraint.Role.USER;

@Controller
@RequiredArgsConstructor
public class AccountController {


    private final AccountService service;


    @PostMapping("/shopping/register")
    public String register(AccountDTO accountDTO, RedirectAttributes redirectAttributes) {
        String email = accountDTO.getEmail();
        String password = accountDTO.getPassword();
        String repeatedPassword = accountDTO.getRepeated();

        try {
            service.register(email, password, repeatedPassword);
            redirectAttributes.addFlashAttribute("emailPasswordSuccess", "Registration was successful. You can now sign in.");
            return "redirect:/shopping/register/reg-success";
        } catch (AccountException e) {
            redirectAttributes.addFlashAttribute("emailPasswordFailure", e.getMessage());
            return "redirect:/shopping/register/failure";
        }
    }
}

