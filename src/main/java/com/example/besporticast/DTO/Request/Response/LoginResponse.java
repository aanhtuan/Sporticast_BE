package com.example.besporticast.DTO.Request.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
        private String status;
        @JsonProperty("is_admin")
        private boolean is_admin;
        private String message;
        private String token;
        private Long user_id;
        private String email;
}
