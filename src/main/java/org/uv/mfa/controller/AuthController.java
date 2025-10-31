package org.uv.mfa.controller;

import org.uv.mfa.entities.User;
import org.uv.mfa.entities.OTPCode;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uv.mfa.service.OTPService;
import org.uv.mfa.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final OTPService otpService;

    public AuthController(UserService userService, OTPService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password,
            @RequestParam(required = false) String machine) {

        Optional<User> user = userService.findByEmail(email);
        if (!user.isPresent() || !userService.checkPassword(user.get(), password)) {
            return ResponseEntity.status(401).body("Incorrect email or password");
        }

        OTPCode otp = otpService.generateCode(user.get(), machine != null ? machine : "default");

        return ResponseEntity.ok("OTP sent to your email");
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateOTP(@RequestParam String email, @RequestParam String code) {
        Optional<User> user = userService.findByEmail(email);
        if (!user.isPresent()) {
            return ResponseEntity.status(404).body("User not found");
        }

        boolean valid = otpService.validateOTP(user.get(), code);
        if (valid) {
            return ResponseEntity.ok("Authentication successful");
        } else {
            return ResponseEntity.status(401).body("Invalid or expired OTP");
        }
    }
}
