package com.example.booking_tour.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.booking_tour.repository.BookingRepository;
import com.example.booking_tour.service.BookingService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import com.example.booking_tour.entity.Booking;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.booking_tour.dto.UpdateBookingRequest;


@Controller("adminBookingController")
@RequestMapping("/admin/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Booking> bookings = bookingRepository.findAll();
        model.addAttribute("bookings", bookings);

        return "admin/bookings/index";
    }

    @PostMapping("")
    public String update(@Valid @ModelAttribute UpdateBookingRequest updateBookingRequest,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        try {
            if (bindingResult.hasErrors()) {
                throw new RuntimeException("Fill in all required fields");
            }
            
            bookingService.updateBookingStatus(updateBookingRequest);
            redirectAttributes.addFlashAttribute("success", "Booking updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/admin/bookings";
    }
}
