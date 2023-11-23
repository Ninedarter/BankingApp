package com.example.BankingApp.service;

import com.example.BankingApp.entity.Account;
import com.example.BankingApp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {


    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        List<Account> all = accountRepository.findAll();
        return all;
    }

    public Account getAccountById(Long accountId) {
        Optional<Account> byId = accountRepository.findById(accountId);
        return byId.get();
    }
}
