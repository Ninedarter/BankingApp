package com.example.BankingApp.controller;


import com.example.BankingApp.entity.Account;
import com.example.BankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/account/")
public class AccountController {


    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @RequestMapping(path = "all/")
    public List<Account> getAccounts() {

        return accountService.getAccounts();
    }

    @GetMapping()
    public Account getAccounts(@RequestParam Long accountId) {
        return accountService.getAccountById(accountId);
    }

}
