package com.example.booking_tour.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.booking_tour.service.UserService;

import jakarta.validation.Valid;

import com.example.booking_tour.config.JwtUtils;
import com.example.booking_tour.dto.JwtResponse;
import com.example.booking_tour.dto.users.LoginRequest;
import com.example.booking_tour.dto.users.RegisterRequest;
import com.example.booking_tour.entity.User;
import com.example.booking_tour.dto.users.UpdateProfileRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Các API liên quan đến xác thực và thông tin người dùng")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Operation(summary = "Đăng nhập", description = "Xác thực người dùng bằng email và mật khẩu để nhận JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Đăng nhập thành công", content = @Content(schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Sai email hoặc mật khẩu", content = @Content(schema = @Schema(implementation = com.example.booking_tour.dto.ApiResponse.class))),
            @ApiResponse(responseCode = "500", description = "Lỗi server")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            // Xác thực user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

            // Lấy UserDetails cho việc tạo token
            UserDetails userDetails = userService.loadUserByUsername(req.getEmail());

            // Generate JWT token
            String token = jwtUtils.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new JwtResponse(token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401)
                    .body(new com.example.booking_tour.dto.ApiResponse<>(false, "Email hoặc mật khẩu không đúng!",
                            null));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new com.example.booking_tour.dto.ApiResponse<>(false, "Lỗi server: " + e.getMessage(), null));
        }
    }

    @Operation(summary = "Đăng ký tài khoản", description = "Tạo tài khoản người dùng mới")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Đăng ký thành công", content = @Content(schema = @Schema(implementation = com.example.booking_tour.dto.ApiResponse.class))),
            @ApiResponse(responseCode = "500", description = "Lỗi server")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {

            User user = userService.createUser(request);

            return ResponseEntity.ok(new com.example.booking_tour.dto.ApiResponse<>(true, "Đăng ký thành công!", user));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new com.example.booking_tour.dto.ApiResponse<>(false, "Lỗi server: " + e.getMessage(), null));
        }
    }

    @Operation(summary = "Lấy thông tin người dùng hiện tại", description = "Yêu cầu JWT token trong header Authorization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lấy thông tin thành công"),
            @ApiResponse(responseCode = "401", description = "Chưa xác thực")
    })
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.ok(new com.example.booking_tour.dto.ApiResponse<>(true, "Chưa đăng nhập", null));
        }

        // Lấy thông tin user đầy đủ từ database
        User user = userService.findByEmail(userDetails.getUsername());
        return ResponseEntity
                .ok(new com.example.booking_tour.dto.ApiResponse<>(true, "Lấy thông tin user thành công!", user));
    }

    @Operation(summary = "Cập nhật hồ sơ người dùng", description = "Cập nhật thông tin cá nhân của người dùng đang đăng nhập")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cập nhật thành công"),
            @ApiResponse(responseCode = "401", description = "Chưa xác thực"),
            @ApiResponse(responseCode = "500", description = "Lỗi server")
    })
    @PostMapping("update-profile")
    public ResponseEntity<?> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid UpdateProfileRequest request) {
        try {
            String currentUserEmail = userDetails.getUsername();

            User updatedUser = userService.updateUserByUser(currentUserEmail, request);

            return ResponseEntity.ok(new com.example.booking_tour.dto.ApiResponse<>(true,
                    "Cập nhật thông tin thành công!", updatedUser));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new com.example.booking_tour.dto.ApiResponse<>(false, "Lỗi server: " + e.getMessage(), null));
        }
    }

}
