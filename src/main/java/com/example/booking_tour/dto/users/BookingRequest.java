package com.example.booking_tour.dto.users;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookingRequest {
    private Integer tour_id;

    @NotBlank(message = "Ngày khởi hành không được để trống")
    private String booking_date;

    @Min(value = 1, message = "Số lượng người phải lớn hơn 0")
    private Integer number_of_people;

    @NotBlank(message = "Họ và tên không được để trống")
    private String full_name;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;

    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
    private String note;
}
