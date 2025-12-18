package com.example.booking_tour.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Yêu cầu đặt tour")
public class BookingRequest {
    @Schema(description = "ID của tour", example = "1")
    private Integer tour_id;

    @NotBlank(message = "Ngày khởi hành không được để trống")
    @Schema(description = "Ngày bắt đầu đi tour (YYYY-MM-DD)", example = "2024-12-25")
    private String booking_date;

    @Min(value = 1, message = "Số lượng người phải lớn hơn 0")
    @Schema(description = "Số lượng người tham gia", example = "2")
    private Integer number_of_people;

    @NotBlank(message = "Họ và tên không được để trống")
    @Schema(description = "Họ tên người đặt", example = "Nguyễn Văn A")
    private String full_name;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Schema(description = "Số điện thoại liên hệ", example = "0987654321")
    private String phone;

    @NotBlank(message = "Email không được để trống")
    @Schema(description = "Email liên hệ", example = "nguyenvana@example.com")
    private String email;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Schema(description = "Địa chỉ liên hệ", example = "Hà Nội")
    private String address;

    @Schema(description = "Ghi chú thêm", example = "Yêu cầu phòng hướng biển")
    private String note;
}
