package com.gridu.store.controller;

import com.gridu.store.dto.PasswordChangeDTO;
import com.gridu.store.dto.RegisterAccountDTO;
import com.gridu.store.model.Account;
import com.gridu.store.service.AccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.security.auth.login.AccountException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;


    //todo {1} cant be transactional
    @PostMapping("/shopping/register")
    public String register(@ModelAttribute RegisterAccountDTO registerAccountDTO, RedirectAttributes redirectAttributes) {

        try {
            Account account = service.createAccountFromRegisterAccountDTO(registerAccountDTO);
            service.register(account);
            redirectAttributes.addFlashAttribute("registerSuccess", "Registration was successful. You can now sign in.");
            return "redirect:/shopping/register/success";
        } catch (AccountException e) {
            redirectAttributes.addFlashAttribute("registerFailure", "You have an account. Please Sign in.");
            return "redirect:/shopping/register/failure";
        }
    }

    @PostMapping("/shopping/password")
    public String changePassword(@ModelAttribute PasswordChangeDTO passwordChangeDTO, RedirectAttributes redirectAttributes, Principal principal) {
        Account loggedAccount = service.findByEmailOrThrow(principal.getName());

        try {
            Account updatedAccount = service.updateAccountWithNewPassword(passwordChangeDTO, loggedAccount);
            service.saveAccount(updatedAccount);
            redirectAttributes.addFlashAttribute("successPassword", "Your password was changed successfully");
            return "redirect:/shopping/password/success";
        } catch (AccountException e) {
            redirectAttributes.addFlashAttribute("passwordChangeFailure", e.getMessage());
            return "redirect:/shopping/register/failure";
        }
    }
}

