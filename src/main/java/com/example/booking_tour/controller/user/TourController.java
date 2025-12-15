package com.example.booking_tour.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking_tour.dto.ApiResponse;
import com.example.booking_tour.dto.LocationStatsDTO;
import com.example.booking_tour.entity.Tour;
import com.example.booking_tour.service.TourService;

@RestController("userTourController")
@RequestMapping("/api/tours")
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAllTours(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {
        try {
            // Parse sort parameters
            String sortField = sort[0];
            String sortDirection = sort.length > 1 ? sort[1] : "asc";

            Sort.Direction direction = sortDirection.equalsIgnoreCase("desc")
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;

            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
            Page<Tour> tourPage = tourService.findAll(pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("data", tourPage.getContent());
            response.put("currentPage", tourPage.getNumber());
            response.put("totalPages", tourPage.getTotalPages());
            response.put("totalItems", tourPage.getTotalElements());
            response.put("pageSize", tourPage.getSize());

            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Lấy thông tin tour thành công!", response));
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Lỗi khi lấy danh sách tour", errorResponse));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Tour>> getTourById(@PathVariable Integer id) {
        Tour tour = tourService.findById(id);
        if (tour == null) {
            return ResponseEntity.ok(new ApiResponse<>(false, "Tour not found with id: " + id, null));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thông tin tour thành công!", tour));
    }

    @GetMapping("/top-locations")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getTopLocations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size) {

        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LocationStatsDTO> locationPage = tourService.findTopLocations(pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("data", locationPage.getContent());
            response.put("currentPage", locationPage.getNumber());
            response.put("totalPages", locationPage.getTotalPages());
            response.put("totalItems", locationPage.getTotalElements());
            response.put("pageSize", locationPage.getSize());

            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Lấy thông tin địa điểm thành công!", response));
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Lỗi khi lấy danh sách địa điểm", errorResponse));
        }
    }
}
