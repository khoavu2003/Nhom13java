package com.example.Bank.Controller;

import com.example.Bank.Entities.User;
import com.example.Bank.Repository.IUserRepository;
import com.example.Bank.Service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SMSService smsService;

    private IUserRepository userRepository;

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        smsService.sendOTP(user);
        return "OTP has been sent";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        boolean isValid = smsService.validateOTP(user, otp);
        if (isValid) {
            return "OTP is valid";
        } else {
            return "OTP is invalid or expired";
        }
    }
}
