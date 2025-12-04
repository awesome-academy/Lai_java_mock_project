package com.example.booking_tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking_tour.entity.Tour;

public interface TourRepository extends JpaRepository<Tour, Long> {
    
}
