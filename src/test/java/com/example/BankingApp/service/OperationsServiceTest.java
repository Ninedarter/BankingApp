package com.example.BankingApp.service;

import com.example.BankingApp.entity.Account;
import com.example.BankingApp.repository.AccountRepository;
import com.example.BankingApp.repository.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OperationsServiceTest {

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private OperationsService operationsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Nested
    @DisplayName("Should allow")
    class ShouldAllow {
        @Test
        @DisplayName("Deposit when amount is positive ")
        void depositWhenAmountIsPositiveAndAccountIsFound() {
            Account tom = new Account(1L, "Tomas", "Kazl", 25, 100.0);
            Account jan = new Account(2L, "Janis", "Test", 29, 1000.0);
            when(accountRepository.findById(1L)).thenReturn(Optional.of(tom));
            Optional<Account> mockedAccount = accountRepository.findById(1L);
            Double balanceBeforeDeposit = mockedAccount.get().getBalance();
            String actualResponse = operationsService.deposit(1L, 500.0);

            Double balanceAfterDeposit = mockedAccount.get().getBalance();
            assertEquals(100, balanceBeforeDeposit);
            assertEquals(600, balanceAfterDeposit);
            assertEquals("Tomas", mockedAccount.get().getFirstName());
            ResponseEntity<String> expectedResponse = new ResponseEntity<>("Deposit successful. New balance: " + balanceAfterDeposit + "€", HttpStatus.OK);
            assertEquals(expectedResponse, actualResponse);

        }

        @Test
        @DisplayName("Check balance when account is valid")
        void checkBalanceWhenAccountIsValid() {
            Account tom = new Account(1L, "Tomas", "Kazl", 25, 100.0);
            Account jan = new Account(2L, "Janis", "Test", 29, 1000.0);

            when(accountRepository.findById(2L)).thenReturn(Optional.of(jan));
            ResponseEntity<Double> expectedResponse = new ResponseEntity<>(1000.0, HttpStatus.OK);
            ResponseEntity<Double> actualResponse = operationsService.getBalance(2L);
            assertEquals(expectedResponse, actualResponse);
        }
    }


    @Nested
    @DisplayName("Should not  allow")
    class ShouldNotAllow {
        @Test
        void withdrawWhenAmountIsNegative() {
        }


    }
}