package com.example.BankingApp.service;

import com.example.BankingApp.entity.Account;
import com.example.BankingApp.exceptions.AccountNotFoundException;
import com.example.BankingApp.exceptions.InvalidAmountException;
import com.example.BankingApp.repository.AccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

;

@Service
public class OperationsService {

    private final AccountRepository accountRepository;
    private static final Logger logger = LogManager.getLogger(OperationsService.class);

    @Autowired
    public OperationsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }

    public String deposit(Long accountId, Double amount) {

        try {
            Account userAccount = getUserAccount(accountId);
            if (accountExits(userAccount)) {
                //<=0
                if (amount.compareTo(0.0D) <= 0) {
                    throw new InvalidAmountException("Amount cannot be negative");
                } else {
                    userAccount.setBalance(userAccount.getBalance() + amount);
                    accountRepository.save(userAccount);
                    logger.info("Deposit with " + amount + "€ was successful");
                    return "Successful";
                }

            } else {
                throw new AccountNotFoundException("Account with id " + accountId + " not found");
            }

        } catch (AccountNotFoundException e) {
            logger.warn("Account not found");
            return "Account not found";
        } catch (InvalidAmountException e) {
            logger.warn("Tried to deposit negative amount");
            return "Amount cannot be negative";
        }

    }


    public ResponseEntity<String> withdraw(Long accountId, Double amount) {
        Account userAccount = getUserAccount(accountId);
        try {
            if (accountExits(userAccount)) {
                if (amount > 0 && amount <= userAccount.getBalance()) {
                    userAccount.setBalance(userAccount.getBalance() - amount);
                    logger.info("Withdraw was successful");
                    accountRepository.save(userAccount);
                    return new ResponseEntity<>("Withdraw successful", HttpStatus.OK);
                } else {
                    throw new InvalidAmountException("Amount should be more than zero, and not exceed balance limit");

                }
            } else {
                throw new AccountNotFoundException("Account not found");

            }

        } catch (InvalidAmountException e) {
            logger.warn("Tried to withdraw negative amount or amount which exceeding balance limits");
            return new ResponseEntity<>("Invalid amount", HttpStatus.BAD_REQUEST);
        } catch (AccountNotFoundException e) {
            logger.warn("Account not found");
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Double> getBalance(Long accountId) {
        Account userAccount = getUserAccount(accountId);
        try {
            if (accountExits(userAccount)) {
                return new ResponseEntity<>(userAccount.getBalance(), HttpStatus.OK);
            } else {
                throw new AccountNotFoundException("Account with id" + accountId + " not found");
            }
        } catch (AccountNotFoundException e) {
            logger.warn("Account with id " + accountId + " not found ");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    private Account getUserAccount(Long accountId) {
        Optional<Account> userAccount = accountRepository.findById(accountId);
        if (userAccount.isPresent()) {
            Account account = userAccount.get();
            return account;
        } else {
            return null;
        }
    }

    private static boolean accountExits(Account userAccount) {
        return userAccount != null;
    }
}
