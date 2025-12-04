package com.example.booking_tour.service;

import com.example.booking_tour.repository.TourRepository;
import com.example.booking_tour.repository.TourImageRepository;
import com.example.booking_tour.entity.Tour;
import com.example.booking_tour.entity.TourImage;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.booking_tour.dto.TourCreateRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.booking_tour.entity.Category;
import com.example.booking_tour.repository.CategoryRepository;

@Service
public class TourService {
    private final TourRepository tourRepository;
    private final TourImageRepository tourImageRepository;
    private final CategoryRepository categoryRepository;

    public TourService(TourRepository tourRepository, TourImageRepository tourImageRepository, CategoryRepository categoryRepository) {
        this.tourRepository = tourRepository;
        this.tourImageRepository = tourImageRepository;
        this.categoryRepository = categoryRepository;
    }

    public Tour createTour(TourCreateRequest request) {
        Category category = categoryRepository.findById(request.getCategory_id())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + request.getCategory_id()));

        Tour tour = new Tour();
        tour.setCategory(category);
        tour.setTitle(request.getTitle());
        tour.setDescription(request.getDescription());
        tour.setLocation(request.getLocation());
        tour.setStart_time(LocalDateTime.parse(request.getStart_time()));
        tour.setEnd_time(LocalDateTime.parse(request.getEnd_time()));
        tour.setPrice(new BigDecimal(request.getPrice()));
        tour.setThumbnail(request.getThumbnail());
        tourRepository.save(tour);

        if (request.getImages() != null && !request.getImages().trim().isEmpty()) {
            String[] imageUrls = request.getImages().split(",");
            List<TourImage> tourImages = new ArrayList<>();
            
            for (String imageUrl : imageUrls) {
                String trimmedUrl = imageUrl.trim();
                if (!trimmedUrl.isEmpty()) {
                    TourImage tourImage = new TourImage();
                    tourImage.setImage_url(trimmedUrl);
                    tourImage.setTour(tour);
                    tourImages.add(tourImage);
                }
            }
            
            tourImageRepository.saveAll(tourImages);
        }

        return tour;
    }

    public Tour updateTour(Integer id, TourCreateRequest request) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found with id: " + id));

        Category category = categoryRepository.findById(request.getCategory_id())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + request.getCategory_id()));

        tour.setCategory(category);
        tour.setTitle(request.getTitle());
        tour.setDescription(request.getDescription());
        tour.setLocation(request.getLocation());
        tour.setStart_time(LocalDateTime.parse(request.getStart_time()));
        tour.setEnd_time(LocalDateTime.parse(request.getEnd_time()));
        tour.setPrice(new BigDecimal(request.getPrice()));
        tour.setThumbnail(request.getThumbnail());
        tourRepository.save(tour);

        // For simplicity, not updating images in this method

        return tour;
    }

    public Tour deleteTour(Integer id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found with id: " + id));
        tourRepository.delete(tour);
        return tour;
    }
}
