package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bean.Account;
import com.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/create")
    public String createAccount(@ModelAttribute Account account, Model model) {
        String result = accountService.createAccount(account);
        model.addAttribute("message", result);
        return "result";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Long accno, @RequestParam double amount, @RequestParam(required = false) String panCardNumber, Model model) {
        String result = accountService.deposit(accno, amount, panCardNumber);
        model.addAttribute("message", result);
        return "result";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long accno, @RequestParam double amount, Model model) {
        String result = accountService.withdraw(accno, amount);
        model.addAttribute("message", result);
        return "result";
    }

    @GetMapping("/balance")
    public String checkBalance(@RequestParam Long accno, Model model) {
        double balance = accountService.checkBalance(accno);
        if (balance == -1) {
            model.addAttribute("message", "Account not found.");
        } else {
            model.addAttribute("balance", balance);
        }
        return "result";
    }
}
