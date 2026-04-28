package com.klu.citizen_connect_backend.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public boolean sendOtpEmail(String toEmail, String username, String otp) {
        return true;
    }

    public boolean sendLoginEmail(String toEmail, String username) {
        return true;
    }
}
