package com.example.BankingApp.repository;

import com.example.BankingApp.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {


}
