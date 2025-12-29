package com.example.booking_tour.service;

import com.example.booking_tour.dto.RevenueReportDTO;
import com.example.booking_tour.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final BookingRepository bookingRepository;

    public List<RevenueReportDTO> getDailyRevenue() {
        return bookingRepository.getDailyRevenue().stream()
                .map(p -> new RevenueReportDTO(p.getPeriod(), p.getRevenue(), p.getOrderCount()))
                .toList();
    }

    public List<RevenueReportDTO> getMonthlyRevenue() {
        return bookingRepository.getMonthlyRevenue().stream()
                .map(p -> new RevenueReportDTO(p.getPeriod(), p.getRevenue(), p.getOrderCount()))
                .toList();
    }
}
