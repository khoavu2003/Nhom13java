package com.example.Bank.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "bank_accounts")
public class bank_accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @NotNull
    @Column(name = "account_number", unique = true, length = 20)
    private String accountNumber;


    @ManyToOne
    @JoinColumn(name="account_type",referencedColumnName = "type_id")
    private AccountType accountType;

    @NotNull
    @Column(name = "Pin", length = 6)
    private String pin;

    @NotNull
    @Column(name = "is_active")
    private Boolean isActive = true;

    @NotNull
    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;
    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)


    private List<transaction> transactions;

    // Getters and Setters
    // ...
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}

