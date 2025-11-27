package com.example.booking_tour.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "Tên không được để trống")
    @jakarta.validation.constraints.Size(max = 100, message = "Tối đa 100 ký tự")
    private String name ;

    @Column(nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "Mô tả không được để trống")
    private String description;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime updated_at;
}
