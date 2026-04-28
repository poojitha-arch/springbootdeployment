package com.klu.citizen_connect_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.citizen_connect_backend.dto.AuthResponse;
import com.klu.citizen_connect_backend.dto.LoginRequest;
import com.klu.citizen_connect_backend.dto.OtpRequest;
import com.klu.citizen_connect_backend.dto.SignRequest;
import com.klu.citizen_connect_backend.dto.VerifyOtpRequest;
import com.klu.citizen_connect_backend.service.AuthService;
import com.klu.citizen_connect_backend.service.OtpService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private OtpService otpService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody SignRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody OtpRequest request) {
        return ResponseEntity.ok(otpService.sendOtp(request));
    }

    @PostMapping("/verify-otp-register")
    public ResponseEntity<AuthResponse> verifyOtpAndRegister(@RequestBody VerifyOtpRequest request) {
        return ResponseEntity.ok(
                otpService.verifyOtpAndRegister(request.getEmail(), request.getOtp())
        );
    }
}
