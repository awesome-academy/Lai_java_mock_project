package com.example.booking_tour.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.booking_tour.dto.users.BookingRequest;
import com.example.booking_tour.entity.Booking;
import com.example.booking_tour.entity.Tour;
import com.example.booking_tour.repository.BookingRepository;
import com.example.booking_tour.repository.TourRepository;
import com.example.booking_tour.repository.UserRepository;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, TourRepository tourRepository,
            UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
    }

    public Booking createBooking(BookingRequest request) {
        try {
            String email = org.springframework.security.core.context.SecurityContextHolder.getContext()
                    .getAuthentication().getName();
            com.example.booking_tour.entity.User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Tour tour = tourRepository.findById(request.getTour_id())
                    .orElseThrow(() -> new IllegalArgumentException("Tour not found with id: " + request.getTour_id()));

            BigDecimal totalPrice = tour.getPrice().multiply(new BigDecimal(request.getNumber_of_people()));

            Booking booking = new Booking();
            booking.setUser(user);
            booking.setTour(tour);
            booking.setBooking_date(request.getBooking_date());
            booking.setNumber_of_people(request.getNumber_of_people());
            booking.setFull_name(request.getFull_name());
            booking.setPhone(request.getPhone());
            booking.setEmail(request.getEmail());
            booking.setAddress(request.getAddress());
            booking.setNote(request.getNote());
            booking.setStatus(Booking.Status.PENDING);
            booking.setTotal_price(totalPrice);
            return bookingRepository.save(booking);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi khi tạo booking: " + e.getMessage());
            throw new RuntimeException("Failed to create booking", e);
        }
    }

    public Object findAll() {
        return bookingRepository.findAll();
    }

}
