package com.example.booking_tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking_tour.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
