package com.example.BankingApp.config;

import com.example.BankingApp.entity.Account;
import com.example.BankingApp.entity.Operation;
import com.example.BankingApp.repository.AccountRepository;
import com.example.BankingApp.repository.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class AppConfig {
    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository, OperationRepository operationRepository) {

        return args -> {

            //Object created by builder pattern.
            Account gaga = Account.builder()
                    .age(24)
                    .firstName("Lady")
                    .lastName("Gaga")
                    .balance(0.0)
                    .build();

            Account karina = Account.builder().
                    age(35)
                    .lastName("Test")
                    .firstName("Karina")
                    .balance(0.0)
                    .build();

             //Object created by using constructor
            Account madars = new Account("Madars","Berzins",25);

            Operation operation1 = new Operation("Transfer", "2020-11-10");
            Operation operation2 = new Operation("Deposit", "2023-11-10");
            Operation operation3 = new Operation("Deposit", "2023-11-19");
            operation1.setAccount(gaga);
            operation2.setAccount(gaga);
            operation3.setAccount(gaga);

            List<Operation> operations = List.of(operation1,operation2,operation3);
            List<Account> accounts = List.of(gaga, karina, madars);
            accountRepository.saveAll(accounts);
            operationRepository.saveAll(operations);
        };
    }


}


