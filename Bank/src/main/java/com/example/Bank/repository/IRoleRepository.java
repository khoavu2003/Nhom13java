package com.example.Bank.repository;

import com.example.Bank.Entities.role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository  extends JpaRepository<role, Long> {
    role findRoleById(Long id);
}
