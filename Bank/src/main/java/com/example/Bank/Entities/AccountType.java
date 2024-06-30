package com.example.Bank.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account_type")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @NotNull
    @Column(name = "name", length = 200)
    private String name;

    @OneToMany(mappedBy = "accountType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<bank_accounts> bankAccounts;
}
