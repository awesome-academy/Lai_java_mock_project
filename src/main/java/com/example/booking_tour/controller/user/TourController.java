package com.example.booking_tour.controller.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking_tour.dto.ApiResponse;
import com.example.booking_tour.entity.Tour;
import com.example.booking_tour.service.TourService;

@RestController
@RequestMapping("/api/tours")
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Tour>>> getAllTours() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thông tin tour thành công!", tourService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Tour>> getTourById(@PathVariable Integer id) {
        Tour tour = tourService.findById(id);
        if (tour == null) {
            return ResponseEntity.ok(new ApiResponse<>(false, "Tour not found with id: " + id, null));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thông tin tour thành công!", tour));
    }
}
