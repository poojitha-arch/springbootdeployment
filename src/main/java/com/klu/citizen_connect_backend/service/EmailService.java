package com.klu.citizen_connect_backend.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public boolean sendOtpEmail(String toEmail, String username, String otp) {
        System.out.println("======================================");
        System.out.println("OTP GENERATED");
        System.out.println("Email: " + toEmail);
        System.out.println("Username: " + username);
        System.out.println("OTP: " + otp);
        System.out.println("======================================");

        return true;
    }

    public boolean sendLoginEmail(String toEmail, String username) {
        System.out.println("Login email skipped for: " + toEmail);
        return true;
    }
}
