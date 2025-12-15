package com.example.booking_tour.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.booking_tour.dto.ApiResponse;
import com.example.booking_tour.dto.users.BookingRequest;
import com.example.booking_tour.entity.Booking;
import com.example.booking_tour.service.BookingService;

@Controller
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAllBookings() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Lấy thông tin booking thành công!", bookingService.findAll()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Lỗi khi lấy danh sách booking", null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Booking>> createBooking(@RequestBody BookingRequest request) {
        try {
            Booking booking = bookingService.createBooking(request);

            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Tạo booking thành công!", booking));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Lỗi khi tạo booking", null));
        }
    }
}
