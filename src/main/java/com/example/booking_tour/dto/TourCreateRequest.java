package com.example.booking_tour.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TourCreateRequest {
    @NotNull(message = "Category is required")
    private Integer category_id;

    @NotBlank(message = "Tour name is required")
    @Size(min = 2, max = 255, message = "Tour name must be between 2 and 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Start time is required")
    private String start_time;

    @NotBlank(message = "End time is required")
    private String end_time;

    @NotBlank(message = "Price is required")
    private String price;

    @NotBlank(message = "Thumbnail URL is required")
    private String thumbnail;

    private String images;
}
