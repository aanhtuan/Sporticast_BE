package com.example.besporticast.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.context.annotation.Bean;


@Data
public class    RegisterRequest {

    @NotEmpty(message = "Vui lòng nhập tên người dùng")
    public String name;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Email không hợp lệ")
    @NotEmpty(message = "hông được bỏ trống")
    public String email;

    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    public String password;

}

