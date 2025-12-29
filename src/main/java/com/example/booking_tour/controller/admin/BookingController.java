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
    private final com.example.booking_tour.service.ExcelService excelService;

    public BookingController(BookingService bookingService, BookingRepository bookingRepository,
            com.example.booking_tour.service.ExcelService excelService) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
        this.excelService = excelService;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Booking> bookings = bookingRepository.findAllByOrderByIdDesc();
        model.addAttribute("bookings", bookings);

        return "admin/bookings/index";
    }

    @GetMapping("/export")
    public org.springframework.http.ResponseEntity<org.springframework.core.io.Resource> exportToExcel()
            throws java.io.IOException {
        List<Booking> bookings = bookingRepository.findAllByOrderByIdDesc();
        java.io.ByteArrayInputStream in = excelService.exportBookingsToExcel(bookings);

        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=bookings.xlsx");

        return org.springframework.http.ResponseEntity.ok()
                .headers(headers)
                .contentType(org.springframework.http.MediaType
                        .parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new org.springframework.core.io.InputStreamResource(in));
    }

    @PostMapping("/import")
    public String importFromExcel(
            @org.springframework.web.bind.annotation.RequestParam("file") org.springframework.web.multipart.MultipartFile file,
            RedirectAttributes redirectAttributes) {
        try {
            int count = excelService.importBookingsFromExcel(file);
            redirectAttributes.addFlashAttribute("success", "Đã import thành công " + count + " bản ghi.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi import: " + e.getMessage());
        }
        return "redirect:/admin/bookings";
    }

    @PostMapping("")
    public String update(@Valid @ModelAttribute UpdateBookingRequest updateBookingRequest,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
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
