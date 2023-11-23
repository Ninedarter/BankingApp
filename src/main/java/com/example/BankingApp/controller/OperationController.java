package com.example.BankingApp.controller;

import com.example.BankingApp.service.OperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/account/operation/")
public class OperationController {


    private final OperationsService operationsService;

    @Autowired
    public OperationController(OperationsService operationsService) {
        this.operationsService = operationsService;
    }


    @GetMapping("balance/")
    public ResponseEntity<Double> getBalance(@RequestParam("accountId") Long accountId) {
        return operationsService.getBalance(accountId);
    }


    @PutMapping("withdraw/")
    public ResponseEntity<String> withDraw(@RequestParam("accountId") Long accountId,
                                           @RequestParam("amount") Double amount) {
        return operationsService.withdraw(accountId, amount);
    }

    @PutMapping("deposit/")
    public ResponseEntity<String> deposit(@RequestParam("accountId") Long accountId,
                                          @RequestParam("amount") Double amount) {
        String response = operationsService.deposit(accountId, amount);
        switch (response) {
            case "Successful" -> {
                return new ResponseEntity<>("Deposit with " + amount + "€ was successful", HttpStatus.OK);
            }
            case "Amount cannot be negative" -> {
                return new ResponseEntity<>("Amount should be more than 0", HttpStatus.BAD_REQUEST);
            }
            default -> {
                return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
            }

        }

    }
}
