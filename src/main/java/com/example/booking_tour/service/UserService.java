package com.example.booking_tour.service;

import com.example.booking_tour.dto.PasswordUpdateRequest;
import com.example.booking_tour.dto.UserCreateRequest;
import com.example.booking_tour.dto.UserUpdateRequest;
import com.example.booking_tour.dto.users.RegisterRequest;
import com.example.booking_tour.entity.User;
import com.example.booking_tour.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.booking_tour.exception.EmailAlreadyExistsException;
import com.example.booking_tour.exception.UserNotFoundException;

import java.util.Optional;
import java.util.Collections;

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

    public User createUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email đã được sử dụng!");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(User.Role.USER);
        user.setProvider(User.Provider.LOCAL);

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

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user với email: " + email));

        // Tạo UserDetails từ User entity
        return new org.springframework.security.core.userdetails.User(
                foundUser.getEmail(),
                foundUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + foundUser.getRole().name())));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user với email: " + email));
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
