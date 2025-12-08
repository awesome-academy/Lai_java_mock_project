package com.example.booking_tour.controller.user;

import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAuthController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "User Login");
        return "user/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register");
        return "user/register";
    }

    @GetMapping("/test-static")
    public String test() {
        URL resource = getClass().getClassLoader().getResource("static/index.html");
        return resource != null ? resource.toString() : "static not found";
    }
}
