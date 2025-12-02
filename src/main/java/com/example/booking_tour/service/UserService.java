package com.example.booking_tour.service;

import com.example.booking_tour.dto.PasswordUpdateRequest;
import com.example.booking_tour.dto.UserCreateRequest;
import com.example.booking_tour.dto.UserUpdateRequest;
import com.example.booking_tour.entity.User;
import com.example.booking_tour.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.booking_tour.exception.EmailAlreadyExistsException;
import com.example.booking_tour.exception.UserNotFoundException;

import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public User createUser(UserCreateRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setAvatar(request.getAvatar());

        try {
            user.setRole(User.Role.valueOf(request.getRole()));
            user.setProvider(User.Provider.valueOf(request.getProvider()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role or provider value");
        }
        
        return userRepository.save(user);
    }
    
    public User updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!user.getEmail().equals(request.getEmail())) {
            Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
            if (existingUser.isPresent()) {
                throw new EmailAlreadyExistsException("Email already exists");
            }
        }
        
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAvatar(request.getAvatar());
        user.setRole(User.Role.valueOf(request.getRole()));
        if (request.getProvider() != null) {
            user.setProvider(User.Provider.valueOf(request.getProvider()));
        }
        
        return userRepository.save(user);
    }
    
    public void updatePassword(Long id, PasswordUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
