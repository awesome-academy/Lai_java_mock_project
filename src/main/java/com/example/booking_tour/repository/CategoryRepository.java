package com.example.booking_tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booking_tour.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
