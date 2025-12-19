package com.example.booking_tour.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Thông tin đăng nhập")
public class LoginRequest {
    @Schema(description = "Email của người dùng", example = "user@example.com")
    private String email;

    @Schema(description = "Mật khẩu người dùng", example = "123456")
    private String password;
}
