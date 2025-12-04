package com.example.booking_tour.controller.admin;

import java.util.Locale.Category;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.booking_tour.dto.TourCreateRequest;
import com.example.booking_tour.entity.Tour;
import com.example.booking_tour.repository.CategoryRepository;
import com.example.booking_tour.repository.TourRepository;
import com.example.booking_tour.service.TourService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin/tours")
public class TourController {
    private final TourRepository tourRepository;
    private final TourService tourService;
    private final CategoryRepository categoryRepository;

    public TourController(TourRepository tourRepository, TourService tourService, CategoryRepository categoryRepository) {
        this.tourRepository = tourRepository;
        this.tourService = tourService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    @Transactional(readOnly = true)
    public String index(Model model) {
        model.addAttribute("title", "Tour Management");
        model.addAttribute("tours", tourRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());

        return "admin/tours/index";
    }

    @PostMapping("")
    public String store(@Valid @ModelAttribute TourCreateRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                throw new RuntimeException("Fill in all required fields");
            }
            
            tourService.createTour(request);

            // Call service to create tour (service not implemented in this snippet)
            redirectAttributes.addFlashAttribute("success", "Tour created successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/admin/tours";
    }

    @GetMapping("/{tourId}")
    @ResponseBody
    @Transactional(readOnly = true)
    public ResponseEntity<Tour> show(@PathVariable Long tourId) {
        Tour tour = tourRepository.findById(tourId)
            .orElseThrow(() -> new RuntimeException("Tour not found with id: " + tourId));
        
        return ResponseEntity.ok(tour);
    }

    @PostMapping("/{tourId}")
    public String update(@PathVariable Long tourId, @Valid @ModelAttribute TourCreateRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                throw new RuntimeException("Fill in all required fields"); 
            }
            tourService.updateTour(tourId, request);
            redirectAttributes.addFlashAttribute("success", "Tour updated successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/tours";
    }

    @PostMapping("/{tourId}/delete")
    public ResponseEntity<Object> delete(@PathVariable Long tourId, RedirectAttributes redirectAttributes) {
        try {
            tourService.deleteTour(tourId);
            redirectAttributes.addFlashAttribute("success", "Tour deleted successfully!");

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}