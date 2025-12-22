package com.example.booking_tour.repository;

import com.example.booking_tour.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    java.util.List<Booking> findByUserEmailOrderByIdDesc(String email);
    java.util.List<Booking> findAllByOrderByIdDesc();
}
