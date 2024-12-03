package com.example.Bank.repository;

import com.example.Bank.Entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<user, Long> {
    Optional<user> findByphoneNumber(String phoneNumber);
    //boolean existsByEmail(String email);
    //boolean existsByphoneNumber(String phoneNumber);
}
