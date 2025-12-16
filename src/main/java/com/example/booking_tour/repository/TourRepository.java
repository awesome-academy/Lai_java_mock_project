package com.example.booking_tour.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.booking_tour.entity.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer> {

    @Query(value = "SELECT t.location as location, " +
            "COUNT(*) as tourCount, " +
            "MIN(t.price) as minPrice, " +
            "(SELECT t2.thumbnail FROM tours t2 WHERE t2.location = t.location ORDER BY t2.id ASC LIMIT 1) as thumbnail "
            +
            "FROM tours t " +
            "GROUP BY t.location", countQuery = "SELECT COUNT(DISTINCT t.location) FROM tours t", nativeQuery = true)
    Page<Object[]> findTopLocations(Pageable pageable);
}
