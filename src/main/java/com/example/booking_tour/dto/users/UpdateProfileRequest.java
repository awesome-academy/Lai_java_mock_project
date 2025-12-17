package com.example.booking_tour.dto.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    @NotBlank(message = "Họ tên không được để trống")
    private String name;
    
    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;

    @NotBlank(message = "Email không được để trống")

    private String email;
    private String birthday;
    private String address;
}
