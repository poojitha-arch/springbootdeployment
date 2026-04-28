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
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:3000",
        "https://citizenconnect0.netlify.app"
})
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
        String message = otpService.sendOtp(request);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/verify-otp-register")
    public ResponseEntity<AuthResponse> verifyOtpAndRegister(@RequestBody VerifyOtpRequest request) {
        AuthResponse response = otpService.verifyOtpAndRegister(
                request.getUsername(),
                request.getEmail(),
                request.getPhone(),
                request.getAddress(),
                request.getCity(),
                request.getState(),
                request.getRole(),
                request.getPassword(),
                request.getConfirmPassword(),
                request.getOtp()
        );

        return ResponseEntity.ok(response);
    }
}

