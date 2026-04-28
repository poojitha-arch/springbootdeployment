package com.klu.citizen_connect_backend.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public boolean sendOtpEmail(String toEmail, String username, String otp) {
        System.out.println("OTP for " + toEmail + " is: " + otp);
        return true;
    }

    public boolean sendLoginEmail(String toEmail, String username) {
        System.out.println("Login email for " + username + " - " + toEmail);
        return true;
    }
}
