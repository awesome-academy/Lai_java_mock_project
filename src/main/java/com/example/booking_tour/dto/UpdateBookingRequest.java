package com.example.booking_tour.dto;

import lombok.Data;

@Data
public class UpdateBookingRequest {
    private Integer id;
    private String status;
}
