package com.example.booking_tour.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.booking_tour.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("dailyReports", reportService.getDailyRevenue());
        model.addAttribute("monthlyReports", reportService.getMonthlyRevenue());
        model.addAttribute("title", "Báo cáo doanh thu");
        return "admin/reports";
    }
}
