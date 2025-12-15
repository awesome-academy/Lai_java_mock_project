package com.example.booking_tour.dto;

import java.math.BigDecimal;

public class LocationStatsDTO {
    private String location;
    private Long tourCount;
    private BigDecimal minPrice;
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
