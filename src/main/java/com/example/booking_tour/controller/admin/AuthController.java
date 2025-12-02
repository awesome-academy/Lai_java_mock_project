package com.example.booking_tour.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AuthController {
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "admin/login";
    }
}
