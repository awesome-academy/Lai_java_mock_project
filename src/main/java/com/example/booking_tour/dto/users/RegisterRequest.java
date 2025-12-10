package com.example.booking_tour.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email không được để trống.")
    @Email(message = "Email không hợp lệ.")
    private String email;

    @NotBlank(message = "Password không được để trống.")
    private String password;

    @NotBlank(message = "Tên không được để trống.")
    private String name;

    @NotBlank(message = "Số điện thoại không được để trống.")
    private String phone;
}
