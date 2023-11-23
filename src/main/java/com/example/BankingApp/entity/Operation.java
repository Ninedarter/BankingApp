package com.example.BankingApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bankOperations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;

    private String dateOfOperation;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;


    public Operation(long id, String name, String date) {
        this.id = id;
        this.name = name;
        this.dateOfOperation = date;

    }

    public Operation(String name, String date) {
        this.name = name;
        this.dateOfOperation = date;

    }

}
