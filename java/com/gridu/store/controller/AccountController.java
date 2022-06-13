package com.gridu.store.controller;


import com.gridu.store.DTO.AccountDTO;
import com.gridu.store.model.Account;
import com.gridu.store.repository.AccountRepository;
import com.gridu.store.service.AccountService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.gridu.store.constraint.Role.USER;

@Controller
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;

    }


    @PostMapping("/shopping/register")
    public String register(AccountDTO accountDTO, RedirectAttributes redirectAttributes) {
        String email = accountDTO.getEmail();

        Optional<Account> byEmail = service.findByEmail(email);
        if (byEmail.isPresent()) {
            redirectAttributes.addFlashAttribute("emailPasswordFailure", "You have an account. Please Sign in.");
            return "redirect:/shopping/failure";
        } else if (!accountDTO.getPassword().equals(accountDTO.getRepeated())) {
            redirectAttributes.addFlashAttribute("emailPasswordFailure", "Your password doesn't match. Try again");
            return "redirect:/shopping/failure";
        } else {
            //todo hide password to not be carried by DTO
            Account account = new Account(email, accountDTO.getPassword(), USER);
            service.add(account);
        }
        redirectAttributes.addFlashAttribute("emailPasswordSuccess", "Registration was successful. You can now sign in.");
        return "redirect:/shopping/reg-success";
    }

    @GetMapping("/shopping/failure")
    public String failure() {
        return "failure";
    }

    @GetMapping("/shopping/signin-success")
    public String successSignin() {
        return "signin-success";
    }

    @GetMapping("/shopping/reg-success")
    public String successRegister() {
        return "reg-success";
    }

    @PostMapping("shopping/signin")
    public String signin(AccountDTO accountDTO, RedirectAttributes redirectAttributes) {
        String email = accountDTO.getEmail();
        String password = accountDTO.getPassword();

        Optional<Account> byEmail = service.findByEmail(email);

        if (byEmail.isPresent()) {
            if (password.equals(byEmail.get().getPassword())) {
                redirectAttributes.addFlashAttribute("emailPasswordSuccess", "Signing in was successful. You can now continue shopping.");
                return "redirect:/shopping/signin-success";
            }
        }

        redirectAttributes.addFlashAttribute("emailPasswordFailure", "Your Account email or password doesn't match. Please try again.");
        return "redirect:/shopping/failure";
    }
}
