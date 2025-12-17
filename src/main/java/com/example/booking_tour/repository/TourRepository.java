package com.example.booking_tour.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("""
        SELECT t FROM Tour t
        WHERE (:keywords IS NULL 
            OR LOWER(t.title) LIKE LOWER(CONCAT('%', :keywords, '%')))
        AND (:startDate IS NULL
            OR t.start_time >= :startDateTime)
    """)
    Page<Tour> findByFilters(
        @Param("keywords") String keywords,
        @Param("startDate") LocalDate startDate,
        @Param("startDateTime") LocalDateTime startDateTime,
        Pageable pageable
    );
}
