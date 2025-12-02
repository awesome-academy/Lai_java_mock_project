package com.example.booking_tour.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tour_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "Tên không được để trống")
    @jakarta.validation.constraints.Size(max = 255, message = "Tối đa 255 ký tự")
    private String image_url;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;
}
