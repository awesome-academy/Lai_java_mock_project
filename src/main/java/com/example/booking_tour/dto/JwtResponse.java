package com.example.booking_tour.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Phản hồi chứa JWT token")
public class JwtResponse {
    @Schema(description = "Chuỗi JWT token dùng để xác thực", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;
}
