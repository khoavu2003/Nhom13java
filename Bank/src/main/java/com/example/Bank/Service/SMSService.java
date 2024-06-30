package com.example.Bank.Service;

import com.example.Bank.Entities.User;
import com.example.Bank.Entities.bank_accounts;
import com.example.Bank.Repository.BankRepository;
import com.example.Bank.Repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SMSService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    private static final String ACCOUNT_SID = "AC0cbc3d09e84fc43aad955f8cf053456c";
    private static final String AUTH_TOKEN = "78ffb306a1e611c7af41e2287b592d09";
    private static final String TWILIO_PHONE_NUMBER = "+12075078400";

    private static final int MAX_RESEND_ATTEMPTS = 5;
    private static final long OTP_EXPIRATION_TIME = 5 * 60 * 1000; // 5 minutes in milliseconds

    private Map<String, String> otpStorage = new HashMap<>();
    private Map<String, Long> otpExpiration = new HashMap<>();
    private Map<String, Integer> otpResendCount = new HashMap<>();

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendPin(bank_accounts bankAccounts) {
        String pin = generatePin();
        bankAccounts.setPin(pin); // Lưu mã PIN vào tài khoản ngân hàng

        // Gửi mã PIN qua SMS
        sendSms(bankAccounts.getUser().getPhoneNumber(), "Mã PIN của bạn là: " + pin + ". Vui lòng bảo mật thông tin này. Nếu muốn thay đổi mã PIN, vui lòng đăng nhập lại. Trân trọng!");

        // Mã hóa PIN trước khi lưu vào cơ sở dữ liệu
        bankAccounts.setPin(new BCryptPasswordEncoder().encode(pin));
        bankRepository.save(bankAccounts);
    }

    private String generatePin() {
        Random random = new Random();
        int pinNumber = 1000 + random.nextInt(9000); // Sinh mã PIN từ 1000 đến 9999
        return String.valueOf(pinNumber);
    }

    private String generateOTP() {
        Random random = new Random();
        int otpNumber = 100000 + random.nextInt(900000); // Sinh mã OTP từ 100000 đến 999999
        return String.valueOf(otpNumber);
    }

    public void sendOTP(User user) {
        String phoneNumber = user.getPhoneNumber();

        if (otpResendCount.getOrDefault(phoneNumber, 0) >= MAX_RESEND_ATTEMPTS) {
            throw new RuntimeException("Bạn đã yêu cầu OTP quá số lần cho phép.");
        }

        String otp = generateOTP();
        long expirationTime = System.currentTimeMillis() + OTP_EXPIRATION_TIME;

        otpStorage.put(phoneNumber, otp);
        otpExpiration.put(phoneNumber, expirationTime);
        otpResendCount.put(phoneNumber, otpResendCount.getOrDefault(phoneNumber, 0) + 1);

        sendSms(phoneNumber, "Mã OTP của bạn là: " + otp + ". Mã có hiệu lực trong vòng 5 phút. Vì lý do bảo mật, tuyệt đối không được cung cấp mã OTP này cho bất kỳ ai. Trân trọng!");
    }

    public boolean validateOTP(User user, String otp) {
        String phoneNumber = user.getPhoneNumber();
        String storedOtp = otpStorage.get(phoneNumber);
        Long expirationTime = otpExpiration.get(phoneNumber);

        if (storedOtp == null || expirationTime == null) {
            return false;
        }

        if (System.currentTimeMillis() > expirationTime) {
            otpStorage.remove(phoneNumber);
            otpExpiration.remove(phoneNumber);
            otpResendCount.remove(phoneNumber);
            return false;
        }

        if (storedOtp.equals(otp)) {
            otpStorage.remove(phoneNumber);
            otpExpiration.remove(phoneNumber);
            otpResendCount.remove(phoneNumber);
            return true;
        }

        return false;
    }

    private void sendSms(String phoneNumber, String message) {
        try {
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(TWILIO_PHONE_NUMBER),
                    message
            ).create();
        } catch (Exception e) {
            // Xử lý khi gửi SMS không thành công
            e.printStackTrace();
            // Ném lại ngoại lệ hoặc xử lý theo ý muốn của bạn
        }
    }
}
