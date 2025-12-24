package com.example.booking_tour.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.booking_tour.service.BookingService;

@Component
public class BookingScheduler {
    private final BookingService bookingService;

    public BookingScheduler(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void cancelExpiredBookingsJob() {
        bookingService.cancelExpiredBookings();
    }
}
