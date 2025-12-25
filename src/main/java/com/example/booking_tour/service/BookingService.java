package com.example.booking_tour.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.booking_tour.dto.UpdateBookingRequest;
import com.example.booking_tour.dto.users.BookingRequest;
import com.example.booking_tour.entity.Booking;
import com.example.booking_tour.entity.Tour;
import com.example.booking_tour.repository.BookingRepository;
import com.example.booking_tour.repository.TourRepository;
import com.example.booking_tour.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public BookingService(BookingRepository bookingRepository, TourRepository tourRepository,
            UserRepository userRepository, EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
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
            bookingRepository.save(booking);

            emailService.sendBookingSuccessEmail(
                booking.getEmail(),
                booking.getId().toString(),
                booking.getTour().getTitle()
            );
            return booking;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create booking", e);
        }
    }

    public Object findAll() {
        return bookingRepository.findAll();
    }

    public void updateBookingStatus(UpdateBookingRequest updateBookingRequest) {
        Booking booking = bookingRepository.findById(updateBookingRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Booking not found with id: " + updateBookingRequest.getId()));

        try {
            booking.setStatus(Booking.Status.valueOf(updateBookingRequest.getStatus()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status value");
        }

        bookingRepository.save(booking);
    }

    public List<Booking> histories(String email) {
        return bookingRepository.findByUserEmailOrderByIdDesc(email);
    }

    @Async
    @Transactional
    public void cancelExpiredBookings() {
        LocalDateTime expiredTime = LocalDateTime.now().minusHours(24);
        List<Booking> expiredBookings = bookingRepository.findExpiredBookings(Booking.Status.PENDING,
                expiredTime);

        for (Booking booking : expiredBookings) {
            booking.setStatus(Booking.Status.CANCELLED);
        }

        bookingRepository.saveAll(expiredBookings);
    }
}
