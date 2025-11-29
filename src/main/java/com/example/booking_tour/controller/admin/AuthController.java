package com.example.booking_tour.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Controller
@RequestMapping("/admin")
public class AuthController {
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "admin/login";
    }

    @PostMapping("/login")
     public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

    }
    
}
