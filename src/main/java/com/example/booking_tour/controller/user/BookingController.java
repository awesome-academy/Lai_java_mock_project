package com.example.booking_tour.controller.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking_tour.dto.ApiResponse;
import com.example.booking_tour.dto.users.BookingRequest;
import com.example.booking_tour.entity.Booking;
import com.example.booking_tour.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController("userBookingController")
@RequestMapping("/api/booking")
@Tag(name = "Booking", description = "Các API dành cho khách hàng đặt tour")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Lấy danh sách tất cả booking", description = "Lưu ý: API này thường dành cho testing hoặc admin trên client")
    @GetMapping
    public ResponseEntity<com.example.booking_tour.dto.ApiResponse<Object>> getAllBookings() {
        try {
            return ResponseEntity.ok(
                    new com.example.booking_tour.dto.ApiResponse<>(true, "Lấy thông tin booking thành công!",
                            bookingService.findAll()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new com.example.booking_tour.dto.ApiResponse<>(false, "Lỗi khi lấy danh sách booking", null));
        }
    }

    @Operation(summary = "Tạo yêu cầu đặt tour", description = "Khách hàng gửi yêu cầu đặt tour")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Tạo thành công", content = @Content(schema = @Schema(implementation = Booking.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    })
    @PostMapping
    public ResponseEntity<com.example.booking_tour.dto.ApiResponse<Booking>> createBooking(
            @RequestBody BookingRequest request) {
        try {
            Booking booking = bookingService.createBooking(request);

            return ResponseEntity.ok(
                    new com.example.booking_tour.dto.ApiResponse<>(true, "Tạo booking thành công!", booking));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new com.example.booking_tour.dto.ApiResponse<>(false, "Lỗi khi tạo booking", null));
        }
    }

    @Operation(summary = "Lịch sử đặt tours", description = "Khách hàng gửi yêu xem lịch sử đặt tours")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lấy lịch sử thành công", content = @Content(schema = @Schema(implementation = Booking.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Dữ liệu không hợp lệ")
    })
    @GetMapping("histories")
    public ResponseEntity<ApiResponse<List<Booking>>> histories(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            String currentUserEmail = userDetails.getUsername();
            return ResponseEntity.ok(
                    new com.example.booking_tour.dto.ApiResponse<>(true, "Lấy lịch sử đặt tour thành công!",
                            bookingService.histories(currentUserEmail)));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new com.example.booking_tour.dto.ApiResponse<>(false, "Lỗi khi lấy lịch sử đặt tour", null));
        }
    }

}
