package com.example.booking_tour.controller.admin;

import com.example.booking_tour.dto.PasswordUpdateRequest;
import com.example.booking_tour.dto.UserCreateRequest;
import com.example.booking_tour.dto.UserUpdateRequest;
import com.example.booking_tour.entity.User;
import com.example.booking_tour.repository.UserRepository;
import com.example.booking_tour.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    
    private final UserRepository userRepository;
    private final UserService userService;
    
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("title", "User Management");
        return "admin/users/index";
    }

    @PostMapping("/users")
    public String store(@Valid @ModelAttribute("userRequest") UserCreateRequest request,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        try {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("error", "Validation failed");
                return "redirect:/admin/users";
            }
            userService.createUser(request);
            redirectAttributes.addFlashAttribute("success", "User created successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/admin/users";
    }
    
    @PostMapping("/users/update")
    public String update(@Valid @ModelAttribute UserUpdateRequest request,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("error", "Validation failed");
                return "redirect:/admin/users";
            }
            userService.updateUser(request.getId(), request);
            redirectAttributes.addFlashAttribute("success", "User updated successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/admin/users";
    }
    
    @PostMapping("/users/password")
    public String updatePassword(@Valid @ModelAttribute PasswordUpdateRequest request,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("error", "Validation failed");
                return "redirect:/admin/users";
            }
            userService.updatePassword(request.getId(), request);
            redirectAttributes.addFlashAttribute("success", "Password updated successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/admin/users";
    }
}
