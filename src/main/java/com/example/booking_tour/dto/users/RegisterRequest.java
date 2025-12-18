package com.example.booking_tour.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Yêu cầu đăng ký tài khoản mới")
public class RegisterRequest {
    @NotBlank(message = "Email không được để trống.")
    @Email(message = "Email không hợp lệ.")
    @Schema(description = "Email người dùng", example = "newuser@example.com")
    private String email;

    @NotBlank(message = "Password không được để trống.")
    @Schema(description = "Mật khẩu người dùng", example = "password123")
    private String password;

    @NotBlank(message = "Tên không được để trống.")
    @Schema(description = "Họ và tên", example = "Nguyễn Văn A")
    private String name;

    @NotBlank(message = "Số điện thoại không được để trống.")
    @Schema(description = "Số điện thoại", example = "0987654321")
    private String phone;
}
