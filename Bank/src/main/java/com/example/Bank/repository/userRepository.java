package com.example.Bank.repository;

import com.example.Bank.Entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface userRepository  extends JpaRepository<user, Long> {
    user findByphoneNumber(String phoneNumber) ;
}
