package com.example.booking_tour.repository;

import com.example.booking_tour.entity.TourImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TourImageRepository extends JpaRepository<TourImage, Integer> {
    @Transactional
    void deleteByTourId(Integer tourId);
}
