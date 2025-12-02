package com.example.booking_tour.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tours")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "Tên tour không được để trống")
    @jakarta.validation.constraints.Size(max = 255, message = "Tối đa 255 ký tự")
    private String title;

    @Column(nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "Mô tả tour không được để trống")
    private String description;

    @Column(nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "Địa điểm không được để trống")
    private String location;

    @Column(nullable = false)
    @jakarta.validation.constraints.NotNull(message = "Số ngày không được để trống")
    private Integer duration_days;

    @Column(nullable = false)
    @jakarta.validation.constraints.NotNull(message = "Giá không được để trống")
    private BigDecimal price;

    @Column(nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "Ảnh đại diện không được để trống")
    private String thumbnail;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
