package com.example.booking_tour.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryCreateRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be maximum 255 characters")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;
}
    