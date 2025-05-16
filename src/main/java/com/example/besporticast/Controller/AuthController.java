package com.example.besporticast.Controller;

import com.example.besporticast.DTO.Request.LoginRequest;
import com.example.besporticast.DTO.Request.RegisterRequest;
import com.example.besporticast.DTO.Request.Response.LoginResponse;
import com.example.besporticast.DTO.Request.Response.VerifyResponse;
import com.example.besporticast.Service.AuthService;
import com.example.besporticast.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest request) {
        String result = userService.registerUser(request.name, request.email, request.password);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Map<String, Object> loginData = userService.loginUser(request.email, request.password);

        if (loginData == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of("status", "error", "message", "Login Failed")
            );
        }

        // Nếu chưa xác minh OTP
        if (Boolean.FALSE.equals(loginData.get("verified"))) {
            authService.sendVerificationCode(request.email);
            return ResponseEntity.status(HttpStatus.OK).body(
                    Map.of(
                            "status", "verify",
                            "message", "Vui lòng xác minh email",
                            "email", request.email
                    )
            );
        }

        return ResponseEntity.ok(
                new LoginResponse(
                        "success",
                        (Boolean) loginData.get("is_admin"),
                        "Login Success",
                        (String) loginData.get("token"),
                        ((Number) loginData.get("user_id")).longValue(),
                        request.email
                )
        );
    }



    @PostMapping("/verify-code")
    public ResponseEntity<VerifyResponse> verifyCode(
            @RequestParam("email") String email,
            @RequestParam("code") String code) {

        // Kiểm tra mã xác thực
        boolean isValid = authService.verifyCode(email, code);

        if (isValid) {
            userService.updateUserVerifiedStatus(email, true);
            return ResponseEntity.ok(new VerifyResponse("Verify Success")); // 200 OK
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST) // 400
                    .body(new VerifyResponse("Invalid or expired verification code."));
        }

    }



}
