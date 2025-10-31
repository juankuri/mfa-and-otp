package controller;

import entities.OTPCode;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.OTPService;
import service.UserService;

@RestController
@RequestMapping("/otp")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @Autowired
    private UserService userService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateOTP(@RequestParam String email,
            @RequestParam(required = false) String machine) {

        Optional<User> user = userService.findByEmail(email);
        if (!user.isPresent()) {
            return ResponseEntity.status(404).body("User not found");
        }

        OTPCode otp = otpService.generateCode(user.get(), machine != null ? machine : "default");
        return ResponseEntity.ok("OTP sent to email");
    }
}
