package com.example.booking_tour.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Thông tin thống kê theo địa điểm")
public class LocationStatsDTO {
    @Schema(description = "Tên địa điểm", example = "Đà Nẵng")
    private String location;

    @Schema(description = "Số lượng tour tại địa điểm này", example = "15")
    private Long tourCount;

    @Schema(description = "Giá thấp nhất của tour tại địa điểm này", example = "500000")
    private BigDecimal minPrice;

    @Schema(description = "Ảnh đại diện của địa điểm", example = "danang.jpg")
    private String thumbnail;

    // Constructor for JPQL query
    public LocationStatsDTO(String location, Long tourCount, BigDecimal minPrice, String thumbnail) {
        this.location = location;
        this.tourCount = tourCount;
        this.minPrice = minPrice;
        this.thumbnail = thumbnail;
    }

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getTourCount() {
        return tourCount;
    }

    public void setTourCount(Long tourCount) {
        this.tourCount = tourCount;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
