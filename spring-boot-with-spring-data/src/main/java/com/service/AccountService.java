package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Account;
import com.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public String createAccount(Account account) {
        if (account.getAmount() <= 1000) {
            return "Amount must be greater than 1,000.";
        }
        if (accountRepository.existsByAccno(account.getAccno())) {
            return "Account number already exists.";
        }
        accountRepository.save(account);
        return "Account created successfully.";
    }

    public String deposit(Long accno, double amount, String panCardNumber) {
        if (amount > 50000 && (panCardNumber == null || panCardNumber.isEmpty())) {
            return "PAN card number required for deposits over 50,000.";
        }
        Account account = accountRepository.findById(accno).orElse(null);
        if (account != null) {
            account.setAmount(account.getAmount() + amount);
            accountRepository.save(account);
            return "Deposit successful.";
        }
        return "Account not found.";
    }

    public String withdraw(Long accno, double amount) {
        Account account = accountRepository.findById(accno).orElse(null);
        if (account != null) {
            if (account.getAmount() < 1000 + amount) {
                return "Insufficient funds. Minimum balance of 1,000 required.";
            }
            account.setAmount(account.getAmount() - amount);
            accountRepository.save(account);
            return "Withdrawal successful.";
        }
        return "Account not found.";
    }

    public double checkBalance(Long accno) {
        Account account = accountRepository.findById(accno).orElse(null);
        return (account != null) ? account.getAmount() : -1;
    }
}

