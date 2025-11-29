package com.example.booking_tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking_tour.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
