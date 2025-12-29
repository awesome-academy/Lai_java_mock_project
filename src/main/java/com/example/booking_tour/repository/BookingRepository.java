package com.example.booking_tour.repository;

import com.example.booking_tour.entity.Booking;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    java.util.List<Booking> findByUserEmailOrderByIdDesc(String email);

    java.util.List<Booking> findAllByOrderByIdDesc();

    @Query("""
                SELECT b FROM Booking b
                WHERE b.status = :status
                AND b.created_at <= :expiredTime
            """)
    List<Booking> findExpiredBookings(
            @Param("status") Booking.Status status,
            @Param("expiredTime") LocalDateTime expiredTime);

    @Query(value = """
                SELECT
                    DATE_FORMAT(created_at, '%Y-%m-%d') as period,
                    SUM(total_price) as revenue,
                    COUNT(id) as orderCount
                FROM bookings
                WHERE status = 'CONFIRMED'
                GROUP BY period
                ORDER BY period DESC
            """, nativeQuery = true)
    List<com.example.booking_tour.dto.RevenueReportProjection> getDailyRevenue();

    @Query(value = """
                SELECT
                    DATE_FORMAT(created_at, '%Y-%m') as period,
                    SUM(total_price) as revenue,
                    COUNT(id) as orderCount
                FROM bookings
                WHERE status = 'CONFIRMED'
                GROUP BY period
                ORDER BY period DESC
            """, nativeQuery = true)
    List<com.example.booking_tour.dto.RevenueReportProjection> getMonthlyRevenue();
}
