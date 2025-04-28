package com.example.besporticast.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginRequest {
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Email không hợp lệ")
    @NotEmpty(message = "hông được bỏ trống")
    public String email;
    public String password;
}
