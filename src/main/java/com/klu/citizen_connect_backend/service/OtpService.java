package com.klu.citizen_connect_backend.service;


import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.citizen_connect_backend.dto.AuthResponse;
import com.klu.citizen_connect_backend.dto.OtpRequest;
import com.klu.citizen_connect_backend.dto.PendingUserData;
import com.klu.citizen_connect_backend.entity.Role;
import com.klu.citizen_connect_backend.entity.User;
import com.klu.citizen_connect_backend.exception.BadRequestException;
import com.klu.citizen_connect_backend.repository.UserRepository;

@Service
public class OtpService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    private final Map<String, PendingUserData> otpStorage = new ConcurrentHashMap<>();

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }


emailService.sendOtpEmail(request.getEmail(), request.getUsername(), otp);

    public String sendOtp(OtpRequest request) {
        if (request.getPassword() == null || request.getConfirmPassword() == null) {
            throw new BadRequestException("Password fields are required");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered");
        }

        String otp = generateOtp();
        long expiryTime = System.currentTimeMillis() + (5 * 60 * 1000);

        PendingUserData pendingUserData = new PendingUserData(request, otp, expiryTime);
        otpStorage.put(request.getEmail(), pendingUserData);

        emailService.sendOtpEmail(request.getEmail(), request.getUsername(), otp);

        return "OTP sent successfully to email";
    }

    public AuthResponse verifyOtpAndRegister(String email, String otp) {
        PendingUserData pendingData = otpStorage.get(email);

        if (pendingData == null) {
            throw new BadRequestException("No OTP request found for this email");
        }

        if (System.currentTimeMillis() > pendingData.getExpiryTime()) {
            otpStorage.remove(email);
            throw new BadRequestException("OTP expired");
        }

        if (!pendingData.getOtp().equals(otp)) {
            throw new BadRequestException("Invalid OTP");
        }

        OtpRequest request = pendingData.getSignupData();

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());

        try {
            user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        } catch (Exception e) {
            throw new BadRequestException("Invalid role value");
        }

        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);
        otpStorage.remove(email);

        return new AuthResponse(
                "Registration successful",
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole().name()
        );
    }
}
