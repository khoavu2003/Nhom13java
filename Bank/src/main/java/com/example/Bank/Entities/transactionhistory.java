package com.example.Bank.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transaction_history")
public class transactionhistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private transaction transaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "message", length = 200)
    private String message;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    // Getters and Setters

    // ... (omitted for brevity)
}

enum Status {
    PENDING, COMPLETED, CANCELLED
}
