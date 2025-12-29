package com.example.booking_tour.dto;

import java.math.BigDecimal;

public interface RevenueReportProjection {
    String getPeriod();

    BigDecimal getRevenue();

    Long getOrderCount();
}
