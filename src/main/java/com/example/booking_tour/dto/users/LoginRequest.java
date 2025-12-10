package com.example.booking_tour.dto.users;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
