package com.example.besporticast.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class UserDto {
    private String name;
    private String avatar;
}
