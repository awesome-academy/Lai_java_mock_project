package com.example.booking_tour.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueReportDTO {
    private String period;
    private BigDecimal revenue;
    private Long orderCount;
}
