package com.example.Bank.Repository;
import com.example.Bank.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    User findByPhoneNumber(String PhoneNumber);
}
