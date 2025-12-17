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
import com.example.booking_tour.dto.ApiResponse;
import com.example.booking_tour.dto.users.RegisterRequest;
import com.example.booking_tour.entity.User;
import com.example.booking_tour.dto.users.UpdateProfileRequest;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

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
                    .body(new ApiResponse<>(false, "Email hoặc mật khẩu không đúng!", null));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ApiResponse<>(false, "Lỗi server: " + e.getMessage(), null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {

            User user = userService.createUser(request);

            return ResponseEntity.ok(new ApiResponse<>(true, "Đăng ký thành công!", user));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Lỗi server: " + e.getMessage(), null));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Chưa đăng nhập", null));
        }

        // Lấy thông tin user đầy đủ từ database
        User user = userService.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thông tin user thành công!", user));
    }

    @PostMapping("update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody @Valid UpdateProfileRequest request) {
        try {
            User updatedUser = userService.updateUserByUser(request);

            return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật thông tin thành công!", updatedUser));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "Lỗi server: " + e.getMessage(), null));
        }
    }
    
}
